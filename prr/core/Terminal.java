package prr.core;

import java.io.Serializable;
import java.util.HashSet;

import prr.core.exception.IllegalModeException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private HashSet <String> _friends;
  //private List <Client> toNotify;
  private Client _owner;

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

  public Terminal(String key, Client owner) {
    _id = key;
    _friends = new HashSet <String>();
    _owner = owner;
    _debt = 0;
    _payments = 0;
    _mode = TerminalMode.IDLE;  
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

  public double getBalance() {
      return getPayments()-getDebt();
  }
  public TerminalMode getMode() {
    return _mode;
  }
  public Client getOwner() {
    return _owner;
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

  public void setOnIdle() throws IllegalModeException {
    setMode(TerminalMode.IDLE);
  }

  public void setOnSilent() throws IllegalModeException {
    setMode(TerminalMode.SILENCE);
  }

  public void turnOff() throws IllegalModeException {
    setMode(TerminalMode.OFF);
  }

  public void setOnBusy() throws IllegalModeException {
    setMode(TerminalMode.BUSY);
  }


  private void setMode(TerminalMode mode) throws IllegalModeException {
    if (mode == _mode)
      throw new IllegalModeException(mode);
    _mode = mode;
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

  public String toString() {
    // terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
    StringBuilder sb = new StringBuilder();
    sb.append(getId()+"|");
    sb.append(getOwner().getKey()+"|");
    sb.append(getMode()+"|");
    sb.append(getPayments()+"|");
    sb.append(getDebt()+"|");
    for (String friend : getFriends()) {
      sb.append(friend+",");
    }
    return sb.toString();
  }

  public void removeFriend(Terminal f) {
    _friends.remove(f.getId());
  }
}