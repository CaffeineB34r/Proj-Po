package prr.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.Serializable;
import java.io.IOException;

import prr.core.exception.DuplicateKeyException;
import prr.core.exception.IllegalModeException;
import prr.core.exception.InvalidKeyException;
import prr.core.exception.UnknownKeyException;

import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.UnsupportedCommException;

// add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private SortedMap<String, Client> _clients;
  private SortedMap<String, Terminal> _terminals;
  private List<Communication> _communications;

  public Network() {
    _clients = new TreeMap<String, Client>(String.CASE_INSENSITIVE_ORDER);
    _terminals = new TreeMap<String, Terminal>(String.CASE_INSENSITIVE_ORDER);
    _communications = new ArrayList<Communication>();
  }

  /**
   * Register a client in the network.
   * 
   * @param name      client name
   * @param key       client id/key
   * @param taxNumber client tax id
   *
   * @return the new client
   * 
   * @throws DuplicateKeyException if the client key is already in use
   */
  public Client registerClient(String key, String name, int taxNumber) throws DuplicateKeyException {
    if (_clients.containsKey(key)) {
      throw new DuplicateKeyException(key);
    }
    Client c = new Client(key, name, taxNumber);
    _clients.put(key, c);
    return c; // for consistency with terminal registration
  }

  /**
   * Register a terminal in the network.
   * 
   * @param type     type of terminal
   * @param key      terminal key
   * @param clientid key of the client that owns the terminal
   * 
   * @return the new terminal
   * 
   * @throws InvalidKeyException   if the terminal key is not valid
   * @throws DuplicateKeyException if the terminal key is already in use
   * @throws UnknownKeyException   if the client key does not exist
   */
  public Terminal registerTerminal(String type, String key, String clientid)
      throws InvalidKeyException, DuplicateKeyException, UnknownKeyException {
    Terminal t;
    Client owner = getClient(clientid);

    if (_terminals.containsKey(key))
      throw new DuplicateKeyException(key);
    else if (!key.matches("^[0-9]{6}$"))
      throw new InvalidKeyException(key);

    if (type.equals("BASIC"))
      t = new BasicTerminal(key, owner);
    else
      t = new FancyTerminal(key, owner);

    _terminals.put(key, t);
    owner.addTerminal(t);
    return t;
  }

  /**
   * Add a friend to a terminal (not simmetric).
   * 
   * @param terminal key of the terminal
   * @param friend   key of the friend
   * @throws UnknownKeyException if the terminal or friend key does not exist
   */
  public void addFriend(String terminal, String friend) throws UnknownKeyException {
    Terminal t = getTerminal(terminal);
    Terminal f = getTerminal(friend);
    t.addFriend(f);
  }

  /**
   * Remove a friend from a terminal (not simmetric).
   * 
   * @param terminal key of the terminal
   * @param friend   key of the friend
   * @throws UnknownKeyException if the terminal or friend key does not exist
   */
  public void removeFriend(String terminal, String friend) throws UnknownKeyException {
    Terminal t = getTerminal(terminal);
    Terminal f = getTerminal(friend);

    t.removeFriend(f);
  }

  /**
   * Get a client by key.
   * 
   * @param key client key
   * @return the client
   * @throws UnknownKeyException if the client key does not exist
   */
  public Client getClient(String clientKey) throws UnknownKeyException {

    if (_clients.containsKey(clientKey))
      return _clients.get(clientKey);
    else
      throw new UnknownKeyException(clientKey);
  }

  /**
   * Get a terminal by key.
   * 
   * @param key terminal key
   * @return the terminal
   * @throws UnknownKeyException if the terminal key does not exist
   */
  public Terminal getTerminal(String terminalKey) throws UnknownKeyException {
    if (_terminals.containsKey(terminalKey))
      return _terminals.get(terminalKey);
    else
      throw new UnknownKeyException(terminalKey);
  }

  /**
   * Get the payment value for a client.
   * 
   * @param clientId client key
   * @return the client's payment value
   * @throws UnknownKeyException if the client key does not exist
   */
  public long getClientPayments(String clientId) throws UnknownKeyException {
    return getClient(clientId).getPayments();
  }

  /**
   * Get the payment value for a client.
   * 
   * @param clientId client key
   * @return the client's debt value
   * @throws UnknownKeyException if the client key does not exist
   */
  public long getClientDebts(String clientId) throws UnknownKeyException {
    return getClient(clientId).getDebts();
  }

  /**
   * Get a string representation of a Client.
   * 
   * @param Clientid client key
   * @return a string representing the client
   * @throws UnknownKeyException if the client key does not exist
   */
  public String showClient(String Clientid) throws UnknownKeyException {
    Client c = getClient(Clientid);
    StringBuilder sb = new StringBuilder(c.toString());
    if (c.getNotifications().size() > 0) {
      sb.append(System.lineSeparator());
      sb.append(showIf(c.getNotifications(), (o) -> true));
    }
    return sb.toString();
  }

  /**
   * Get a string representation of all Terminals.
   * 
   * @return a string representing the registred terminals
   */
  public String showAllTerminals() {
    return showIf(_terminals.values(), (o) -> true);
  }

  /**
   * Get a string representation of all Clients.
   * 
   * @return a string representing the registred clients
   */
  public String showAllClients() {
    return showIf(_clients.values(), (o) -> true);
  }

  /**
   * Get a string representation of all Terminals without activity.
   * 
   * @return a string representing the registred terminals
   */
  public String showUnusedTerminals() {
    // Activity(making calls) generates Debt for the starting terminal
    // Activity could also be defined as having done OR taken any call

    return showIf(_terminals.values(), (o) -> o.getMadeCommunications().size() + o.getReceivedCommunications().size() == 0);
  }

  public void disableClientNotifications(String clientId) throws UnknownKeyException, IllegalModeException {
    Client c = getClient(clientId);
    c.disableReceiveNotifications();
  }

  public void enableClientNotifications(String clientId) throws UnknownKeyException, IllegalModeException {
    Client c = getClient(clientId);
    c.enableRecieveNotifications();
  }

  public String showClientsWithoutDebts() {
    return showIf(_clients.values(), (o) -> o.getDebts() == 0);
  }

  public String showClientsWithDebts() {
    return showIf(_clients.values(), (o) -> o.getDebts() > 0);
  }

  public void sendTextCommunication(Terminal origin, String destinationKey, String message)
      throws UnknownKeyException, IllegalModeException {
    Terminal destination = getTerminal(destinationKey);
    try {
      TextCommunication comm = new TextCommunication(_communications.size() + 1, destination, origin, message);
      destination.acceptSMS(comm);
      origin.makeSMS(comm);
      _communications.add(comm);
    } catch (IllegalModeException e) {
      destination.addNotification(e.getMode(), "TEXT", origin.getOwner());
      throw e;
    }
  }

  public void startInteractiveCommunication(Terminal origin, String destinationKey, String communicationType)
      throws UnknownKeyException, IllegalModeException, UnsupportedCommException {
    Terminal destination = getTerminal(destinationKey);

    try {
      if (communicationType.equals("VOICE")) {
        VoiceCommunication comm = new VoiceCommunication(_communications.size() + 1, destination, origin);
        origin.makeVoiceCall(comm);
        _communications.add(comm);

      } else if (communicationType.equals("VIDEO")) {
        VideoCommunication comm = new VideoCommunication(_communications.size() + 1, destination, origin);
        origin.makeVideoCall(comm);
        _communications.add(comm);
      }
    } catch (IllegalModeException e) {
      destination.addNotification(e.getMode(), communicationType, origin.getOwner());
      throw e;
    }

  }

  public double endInteractiveCommunication(Terminal origin, int communicationSize) {
    double cost = origin._ongoingCommunication.end(communicationSize);
    origin.addDebt(cost);
    return cost;

  }

  public String showAllCommunications() {
    return showIf(_communications, (o) -> true);
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException                if there is an IO erro while processing
   *                                    the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  /**
   * Generic method to get a string representing the Objects in a
   * collection that satisfy a predicate.
   * 
   * @param <T>    type of the objects in the collection
   * @param col    collection of objects
   * @param filter predicate to filter the objects
   * @return a string representing the objects in the collection
   */
  private <T> String showIf(Collection<T> col, Predicate<T> filter) {
    StringBuilder sb = new StringBuilder("");
    for (T o : col)
      if (filter.test(o))
        sb.append(o.toString()).append(System.lineSeparator());
    if (sb.length() > 0)
      sb.deleteCharAt(sb.length() - 1);
    return sb.toString();

  }

  public String showCommunicationsFromClient(String stringField) throws UnknownKeyException {
    Terminal t = getTerminal(stringField);
    return showIf(t.getMadeCommunications(), (o) -> true);
  }

  public String showCommunicationsToClient(String stringField) throws UnknownKeyException {
    Terminal t = getTerminal(stringField);
    return showIf(t.getReceivedCommunications(), (o) -> true);
  }

  public String showTerminalsWithPositiveBalance() {
    return showIf(_terminals.values(), (o) -> o.getBalance() > 0);
  }

  public double showGlobalBalance() {
    double balance = 0;
    for (Terminal t : _terminals.values()) {
      balance += t.getBalance();
    }
    return balance;
  }

}
