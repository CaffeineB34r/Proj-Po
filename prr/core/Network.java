package prr.core;

import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.io.IOException;

import prr.core.exception.DuplicateKeyException;
import prr.core.exception.InvalidKeyException;
import prr.core.exception.UnknownKeyException;
import prr.core.exception.WrongKeyException;
import prr.core.exception.UnrecognizedEntryException;

// add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private Map<String, Client> _clients;
  private Map<String, Terminal> _terminals;

  public Network() {
    _clients = new TreeMap<String, Client>();
    _terminals = new TreeMap<String, Terminal>();
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

  public void registerClient(String name, String key, int taxNumber) throws DuplicateKeyException {
    if (_clients.containsKey(key)) {
      throw new DuplicateKeyException(key);
    }
    _clients.put(key, new Client(key, name, taxNumber));
  }

  /**
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
    return t;
  }

  public void addFriend(String terminal, String friend) throws UnknownKeyException {
    Terminal t = _terminals.get(terminal);
    Terminal f = _terminals.get(friend);
    if (t == null || f == null)
      throw new UnknownKeyException(terminal);
    t.addFriend(f);
  }

  public void showAllClients() {
    for (Client c : _clients.values())
      System.out.println(c.toString());
  }

  public void showAllTerminals() {
    for (Terminal t : _terminals.values())
      System.out.println(t.toString());
  }

  public Client getClient(String clientKey) throws UnknownKeyException {
    if (_clients.containsKey(clientKey))
      return _clients.get(clientKey);
    else
      throw new UnknownKeyException(clientKey);
  }

  public Terminal getTerminal(String terminalKey) throws UnknownKeyException {
    if (_terminals.containsKey(terminalKey))
      return _terminals.get(terminalKey);
    else
      throw new UnknownKeyException(terminalKey);
  }

  public void removeFriend(String terminal, String friend) throws UnknownKeyException {
    Terminal t = _terminals.get(terminal);
    Terminal f = _terminals.get(friend);
    if (t == null || f == null)
      throw new UnknownKeyException(terminal);
    t.removeFriend(f);
  }

  public String showClient(String stringField) throws WrongKeyException {
    Client c = _clients.get(stringField);
    if (c == null)
      throw new WrongKeyException(stringField);
    return c.toString();
  }

  public String showDebtPayments(String stringField) throws WrongKeyException {
    Client c = _clients.get(stringField);
    if (c == null)
      throw new WrongKeyException(stringField);
    return c.getDebts() + " " + c.getPayments();
  }

}
