package prr.core.exception;

public class InvalidKeyException extends WrongKeyException {
  private static final long serialVersionUID = 201708301010L;

  public InvalidKeyException(String key) {
    super(key);
  }
}
