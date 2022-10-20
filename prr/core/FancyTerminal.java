package prr.core;

import prr.core.exception.UnknowKeyException;

public class FancyTerminal extends Terminal {
    public FancyTerminal(String key, String clientKey, Network network) throws UnknowKeyException {
        super(key, clientKey, network);
    }
}
