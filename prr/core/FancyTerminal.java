package prr.core;

public class FancyTerminal extends Terminal {
    public FancyTerminal(String key,Client owner) {
        super(key, owner);
    }

    public String toString() {
        return "FANCY|" + super.toString();
    }
}
