package prr.core.exception;

//wrong key exception
public class UnknownKeyException extends WrongKeyException {
    private static final long serialVersionUID = 202208091753L;

    public UnknownKeyException(String key) {
        super(key);
    }

    // cause for debugging reasons
    public UnknownKeyException(String key, Throwable cause) {
        super(key, cause);
    }
}