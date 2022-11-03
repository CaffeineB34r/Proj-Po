package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import prr.core.exception.IllegalModeException;

public class Client implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    private final String _key;
    private final String _name;
    private final int _taxNumber;
    private ClientState _level;
    private boolean _receiveNotifications;
    private List<Terminal> _terminals;
    private int _payments;
    private int _debts;
    private List <String> _notifications;



    public Client(String key, String name, int taxId) {
        this._key = key;
        this._name = name;
        this._taxNumber = taxId;
        this._payments = 0;
        this._debts = 0;
        this._terminals = new ArrayList<Terminal>();
        this._level = new NormalClient(this);
        this._receiveNotifications = true;
    }

    // Getters
    public String getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public int getTaxNumber() {
        return _taxNumber;
    }

    public ClientState getLevel() {
        return _level;
    }

    public boolean getReceiveNotifications() {
        return _receiveNotifications;
    }

    public int getTerminalsSize() {
        return _terminals.size();
    }

    public int getPayments() {
        return _payments;
    }

    public int getDebts() {
        return _debts;
    }

    public String toString() {
        // CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
        StringBuilder sb = new StringBuilder("CLIENT|");

        sb.append(getKey() + "|");
        sb.append(getName() + "|");
        sb.append(getTaxNumber() + "|");
        sb.append(getLevel() + "|");
        sb.append((getReceiveNotifications()) ? "YES|" : "NO|");
        sb.append(getTerminalsSize() + "|");
        sb.append(getPayments() + "|");
        sb.append(getDebts());

        return sb.toString();
    }

    public void addTerminal(Terminal terminal) {
        _terminals.add(terminal);
    }

    public void removeTerminal(Terminal terminal) {
        // Not needed, but migh as well have it
        _terminals.remove(terminal);
    }

    public void disableReceiveNotifications() throws IllegalModeException {
        if (getReceiveNotifications()) 
            this._receiveNotifications = false;
        else
            throw new IllegalModeException("NO");
    }

    public void enableRecieveNotifications() throws IllegalModeException {
        if (!getReceiveNotifications())
            this._receiveNotifications = true;
        else
            throw new IllegalModeException("YES");
    }

    public void setLevel(ClientState level) {
        this._level = level;
    }

    public void notify(String notificationMessage) {
        _notifications.add(notificationMessage);
    }


}

abstract class ClientState implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    protected Client _client;

    public ClientState(Client client) {
        this._client = client;
    }

    abstract public double computeCosts(Communication comm);

    abstract public void upgradeState();

    abstract public void downgradeState();



}

class NormalClient extends ClientState {
    public NormalClient(Client client) {
        super(client);
    }

    @Override
    public double computeCosts(Communication comm) {
        return comm.getCost();
    }

    @Override
    public void upgradeState() {
        this._client.setLevel(new GoldClient(this._client));
    }

    @Override
    public void downgradeState() {
    }

    public String toString() {
        return "NORMAL";
    }
}

class GoldClient extends ClientState {

    public GoldClient(Client client) {
        super(client);
    }

    @Override
    public double computeCosts(Communication comm) {
        return comm.getCost() * 0.5;
    }

    @Override
    public void upgradeState() {
        this._client.setLevel(new PlatinumClient(this._client));
    }

    @Override
    public void downgradeState() {
        this._client.setLevel(new NormalClient(this._client));
    }

    public String toString() {
        return "GOLD";
    }
}

class PlatinumClient extends ClientState {

    public PlatinumClient(Client client) {
        super(client);
    }

    @Override
    public double computeCosts(Communication comm) {
        return 0;
    }

    @Override
    public void upgradeState() {
    }

    @Override
    public void downgradeState() {
        this._client.setLevel(new GoldClient(this._client));
    }

    public String toString() {
        return "PLATINUM";
    }
}
