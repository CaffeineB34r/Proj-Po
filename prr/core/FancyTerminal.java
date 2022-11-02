package prr.core;

public class FancyTerminal extends Terminal {
    private static final String _name = "FANCY";

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    public String toString() {
        return _name + "|" + super.toString();
    }
}
