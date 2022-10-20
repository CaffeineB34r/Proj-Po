package prr.core.exception;

public class DuplicateKeyException extends WrongKeyException {

  private static final long serialVersionUID = 201708301010L;

  public DuplicateKeyException(String key) {
    super(key);
  }
  
}
    

