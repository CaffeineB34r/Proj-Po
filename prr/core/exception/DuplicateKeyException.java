package prr.core.exception;

public class DuplicateKeyException extends Exception {

  private static final long serialVersionUID = 201708301010L;

  private String _key;

  public DuplicateKeyException(String key) {
    _key = key;
  }

  public String getKey() {
    return _key;
  }

}
    

