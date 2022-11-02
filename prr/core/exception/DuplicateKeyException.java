package prr.core.exception;

/**
 * Class for representing a registration error due to a duplicate key.
 */
public class DuplicateKeyException extends WrongKeyException {
  private static final long serialVersionUID = 201708301010L;

  public DuplicateKeyException(String key) {
    super(key);
  }
  
}
    

