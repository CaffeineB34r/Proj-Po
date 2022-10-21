package prr.core.exception;

/*
 * Provides an exception root for wrong inputs Exceptions.
 * This makes Debugging easier.
 */
public abstract class WrongKeyException extends Exception {
    private static final long serialVersionUID = 202208091753L;

    public WrongKeyException(String key) {
        super(key);
    }

    public WrongKeyException(String key, Throwable cause) {
        super(key, cause);
    }

    public String getKey() {
        return super.getMessage();
    }
    
    //get cause for debugging reasons
    public Throwable getCause() {
        return super.getCause();
    } 
}
