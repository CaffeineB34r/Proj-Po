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
  
  private Map <String, Client> _clients;
  private Map <String, Terminal> _terminals = new TreeMap <String, Terminal>();
  // FIXME define contructor(s)
  // FIXME define methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
  
  public void registerClient(String name, String key, int taxNumber) {
    _clients.put(key, new Client(key, name, taxNumber));
  }

  public Terminal registerTerminal(String string, String string2, String string3) {
    return null;
  }

  public void addFriend(String terminal, String friend) throws UnknowKeyException {
    Terminal t = _terminals.get(terminal);
    Terminal f = _terminals.get(friend);
    if (t == null || f == null)
      throw new UnknowKeyException(terminal);
    t.addFriend(f);
    f.addFriend(t);

  }
    

  public void showAllClients() {

  }

  public Client getClient(String clientKey){
    return _clients.get(clientKey);
  }
   
  public Terminal getTerminal(String terminalKey){
    return _terminals.get(terminalKey);
  }

}

