package prr.core;

import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.io.IOException;

import prr.core.exception.UnknowKeyException;
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
  void importFile(String filename) throws UnrecognizedEntryException, IOException{
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

  public void registerClient(String name, String key, int taxNumber) {
    _clients.put(key, new Client(key, name, taxNumber));
  }

  public Terminal registerTerminal(String type, String key, String clientid) throws UnknowKeyException {
    Terminal t;
    Client owner = _clients.get(clientid);
    if (owner == null)
      throw new UnknowKeyException(clientid);
    if (type == "BASIC")
      t = new BasicTerminal(key, owner);
    else if (type == "FANCY")
      t = new FancyTerminal(key, owner);
    else
      throw new UnknowKeyException(type);
    _terminals.put(key, t);
    return t;
  }

  public void addFriend(String terminal, String friend) throws UnknowKeyException {
    Terminal t = _terminals.get(terminal);
    Terminal f = _terminals.get(friend);
    if (t == null || f == null)
      throw new UnknowKeyException(terminal);
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

  public Client getClient(String clientKey) {
    return _clients.get(clientKey);
  }

  public Terminal getTerminal(String terminalKey) {
    return _terminals.get(terminalKey);
  }

  public void removeFriend(String terminal, String friend) throws UnknowKeyException {
    Terminal t = _terminals.get(terminal);
    Terminal f = _terminals.get(friend);
    if (t == null || f == null)
      throw new UnknowKeyException(terminal);
    t.removeFriend(f);
  }

  public String showClient(String stringField) throws UnknowKeyException {
    Client c = _clients.get(stringField);
    if (c == null)
      throw new UnknowKeyException(stringField);
    return c.toString();
  }

  public String showDebtPayments(String stringField) throws UnknowKeyException {
    Client c = _clients.get(stringField);
    if (c == null)
      throw new UnknowKeyException(stringField);
    return c.getDebts() + " " + c.getPayments();
  }

}
