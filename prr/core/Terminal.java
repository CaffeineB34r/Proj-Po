package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import prr.core.exception.IllegalModeException;
import prr.core.exception.UnsupportedCommException;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable {

  /** Possible Terminal states */
  public enum TerminalMode {
    OFF,
    BUSY,
    SILENCE,
    IDLE,
  }

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private SortedSet<String> _friends;
  private Notifications _notifications;
  private Client _owner;
  protected InteractiveCommunication _ongoingCommunication;
  private List<Communication> _madeCommunications;

  protected List<Communication> _receivedCommunications;
  private String _id;
  private double _debt;
  private double _payments;

  private TerminalMode _mode;
  private TerminalMode _previousMode;

  /**
   * Creates a new terminal.
   * 
   * @param key   terminal id/key
   * @param owner terminal owner
   */
  public Terminal(String key, Client owner) {
    _id = key;
    _friends = new TreeSet<String>();
    _owner = owner;
    _debt = 0;
    _payments = 0;
    _mode = TerminalMode.IDLE;
    _ongoingCommunication = null;
    _madeCommunications = new ArrayList<>();
    _receivedCommunications = new ArrayList<>();
    _notifications = new Notifications(this);
  }

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
    return getPayments() - getDebt();
  }

  public TerminalMode getMode() {
    return _mode;
  }

  public Client getOwner() {
    return _owner;
  }

  public SortedSet<String> getFriends() {
    return _friends;
  }

  public void addFriend(Terminal friend) {
    _friends.add(friend.getId());
  }

  /**
   * Sends a SMS communication.
   * 
   * @param to      Terminal to send the message to.
   * @param message Message to send.
   * @throws IllegalModeException
   */
  public void makeSMS(TextCommunication comm) throws IllegalModeException {
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    }
    _madeCommunications.add(comm);
  }

  /**
   * Starts a voice communication.
   * 
   * @param to Terminal to start the communication with.
   */
  public void makeVoiceCall(VoiceCommunication comm){
     try {
      if (canStartCommunication()) {
        _ongoingCommunication = comm;
        this.setOnBusy();
        _receivedCommunications.add(comm);
      }
    } catch (IllegalModeException e) {
      //impossible to happen
      e.printStackTrace();
    }
  }

  public void makeVideoCall(VideoCommunication comm) throws UnsupportedCommException {
    try {
      if (canStartCommunication()) {
        _ongoingCommunication = comm;
        this.setOnBusy();
        _receivedCommunications.add(comm);
      }
    } catch (IllegalModeException e) {
      //impossible to happen
      e.printStackTrace();
    }
  }

  public void endOngoingCommunication(int size) {
    if (canEndCurrentCommunication()) {
      _ongoingCommunication.end(size);
      _ongoingCommunication = null;
      this.setOnPreviousMode();
    }
  }

  public void setOnIdle() throws IllegalModeException {
    switch (getMode()) {
      case IDLE -> throw new IllegalModeException("IDLE");
      case BUSY -> {
        _notifications.notifyBusyToIdle();
      }
      case SILENCE -> {
        _notifications.notifySilentToIdle();
      }
      case OFF -> {
      }
    }
    _mode = TerminalMode.IDLE;

  }

  public void setOnSilent() throws IllegalModeException {
    switch (getMode()) {
      case IDLE -> {
      }
      case BUSY -> {
        _notifications.notifyBusyToSilent();
      }
      case SILENCE -> throw new IllegalModeException("SILENCE");
      case OFF -> {
        _notifications.notifyOffToSilent();
      }
    }
    _mode = TerminalMode.SILENCE;
  }

  public void turnOff() throws IllegalModeException {
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    }
    _mode = TerminalMode.OFF;
  }

  public void setOnBusy() throws IllegalModeException {
    this._previousMode = this._mode;
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    }
    _mode = TerminalMode.BUSY;
  }

  // seton previous mode
  public void setOnPreviousMode(){
    this._mode = this._previousMode;
  }

  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive
   *         communication) and
   *         it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    return _ongoingCommunication != null && _ongoingCommunication.getIdSender().getId().equals(this.getId());
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
    sb.append(getId() + "|");
    sb.append(getOwner().getKey() + "|");
    sb.append(getMode() + "|");
    sb.append(Math.round(getPayments()) + "|");
    sb.append(Math.round(getDebt()));
    if (_friends.size() > 0) {
      sb.append("|");
      for (String friend : _friends) {
        sb.append(friend + ",");
      }
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public void removeFriend(Terminal f) {
    _friends.remove(f.getId());
  }

  public void performPayment(int commKey) {

  }

  /**
   * Receives a SMS communication.
   *
   * @param from terminal that sent the message.
   * @throws IllegalModeException
   */
  protected void acceptSMS(TextCommunication comm) throws IllegalModeException {
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    }
    _receivedCommunications.add(comm);
  }

  protected void acceptVoiceCall(VoiceCommunication comm) throws IllegalModeException {
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    } else if (getMode() == TerminalMode.BUSY) {
      throw new IllegalModeException("BUSY");
    } else if (getMode() == TerminalMode.SILENCE) {
      throw new IllegalModeException("SILENCE");
    }
    _ongoingCommunication = comm;
    this.setOnBusy();
    _receivedCommunications.add(comm);
  }

  protected void acceptVideoCall(VideoCommunication comm) throws IllegalModeException, UnsupportedCommException {
    if (getMode() == TerminalMode.OFF) {
      throw new IllegalModeException("OFF");
    } else if (getMode() == TerminalMode.BUSY) {
      throw new IllegalModeException("BUSY");
    } else if (getMode() == TerminalMode.SILENCE) {
      throw new IllegalModeException("SILENCE");
    }
    _ongoingCommunication = comm;
    this.setOnBusy();
    _receivedCommunications.add(comm);
  }
}