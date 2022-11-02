package prr.core.exception;

public class IllegalModeException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private String _mode;

  public IllegalModeException(String mode) {
    _mode = mode;
  }

  public String getMode() {
    return _mode;
  }

}


