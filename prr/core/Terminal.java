package prr.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import prr.core.exception.UnknowKeyException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private HashSet <String> _friends;
  private List <Client> toNotify;
  private Client owner;

  private String _id;
  private double _debt;
  private double _payments;
  private TerminalMode _mode;


  public enum TerminalMode { 
    OFF,
    BUSY,
    SILENCE,
    IDLE,
  }

  public Terminal(String key, String clientKey, Network network) throws UnknowKeyException {
    _id = key;
    _friends = new HashSet <String>();
    owner = network.getClient(clientKey);
    _debt = 0;
    _payments = 0;
    _mode = TerminalMode.IDLE;
    
    if (owner != null) 
      owner.addTerminal(this);
    else
      throw new UnknowKeyException(clientKey);
  }
  
  //getters
  public String getId() {
    return _id;
  }
  public double getDebt() {
    return _debt;
  }
  public double getPayments() {
    return _payments;
  }
  public TerminalMode getMode() {
    return _mode;
  }
  public Client getOwner() {
    return owner;
  }
  public HashSet <String> getFriends() {
    return _friends;
  }
  
  public void addFriend(Terminal friend) {
    _friends.add(friend.getId());
  }

  /**
   * Sends a SMS communication. 
   * 
   * @param to Terminal to send the message to.
   * @param message Message to send.
   */
  public void makeSMS(Terminal to, String message) {
    //FIXME implement method
  }

  /**
   * Receives a SMS communication.
   *
   * @param from terminal that sent the message.
   */
  protected void acceptSMS(Terminal from) {
    //FIXME implement method
  }

  /**
   * Starts a voice communication.
   * 
   * @param to Terminal to start the communication with.
   */
  public void makeVoiceCall(Terminal to) {
    //FIXME implement method
  }

  protected void acceptVoiceCall(Terminal from) {
    //FIXME implement method
  }

  public void makeVideoCall(Terminal to) {
    //FIXME implement method
  }

  protected void acceptVideoCall(Terminal from) {
    //FIXME implement method
  }

  public void endOngoingCommunication(int size) {
    //FIXME implement method
  }

  public void setOnIdle() {
    //FIXME implement method
  }

  public void setOnSilent() {
    //FIXME implement method
  }

  public void turnOff() {
    
  }


  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    return _mode == TerminalMode.BUSY;
    // add check for comms
  }
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
	  return _mode != TerminalMode.OFF && _mode != TerminalMode.BUSY;
  }
}