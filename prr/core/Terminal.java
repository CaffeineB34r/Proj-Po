package prr.core;

import java.io.Serializable;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private String _id;
  private double _debt;
  private double _payments;
  private TerminalMode _mode;


  private enum TerminalMode { 
    OFF,
    BUSY,
    SILENCE,
    ON,
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

  public boolean setOnIdle() {
    if (this._mode != TerminalMode.I)
    //FIXME implement method
  }

  public boolean setOnSilent() {
    //FIXME implement method
  }

  public boolean turnOff() {
    //FIXME implement method
  }



  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    return _mode == TerminalMode.BUSY;
    // FIXME add check for comms
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
