package prr.core.exception;
import prr.core.Terminal.TerminalMode;

public class IllegalModeException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private TerminalMode _mode;

  public IllegalModeException(TerminalMode mode) {
    _mode = mode;
  }

  public TerminalMode getMode() {
    return _mode;
  }

}


