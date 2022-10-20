package prr.core;

import prr.core.exception.UnknowKeyException;

public class BasicTerminal extends Terminal {
    public BasicTerminal(String key, String clientKey, Network network) throws UnknowKeyException {
        super(key, clientKey, network);
    }
}
