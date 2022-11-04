package prr.core;

import prr.core.exception.UnsupportedCommException;

/**
 * Class BasicTerminal implements a basic terminal.
 * (can't make video calls)
 */
public class BasicTerminal extends Terminal {

    // For differentiation with the other terminals. 
    private static final String _name = "BASIC";

    public BasicTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    protected void acceptVideoCall(VideoCommunication comm) throws UnsupportedCommException {
        throw new UnsupportedCommException("DESTINATION");
    }

    @Override
    public void makeVideoCall(VideoCommunication comm) throws UnsupportedCommException {
        throw new UnsupportedCommException("SOURCE");
    }

    @Override
    public String toString() {
        return _name + "|" + super.toString();
    }
}
