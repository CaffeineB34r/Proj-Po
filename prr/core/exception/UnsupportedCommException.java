package prr.core.exception;

public class UnsupportedCommException extends Exception {
    private static final long serialVersionUID = 202208091753L;

    public UnsupportedCommException(String unsupportedat) {
        super(unsupportedat);
    }

    public String getUnsupportedAt() {
        return super.getMessage();
    }
    
}
