package prr.core;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Notifications implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    private List<Client> _offToSilent;
    private List<Client> _silentToIdle;
    private List<Client> _busyToIdle;
    private List<Client> _busyToSilent;
    private Terminal _terminal;

    public enum NotificationType {
        OFF_TO_SILENT,
        SILENT_TO_IDLE,
        BUSY_TO_SILENT,
        BUSY_TO_IDLE
    }

    public Notifications(Terminal context) {
        this._offToSilent = new ArrayList<Client>();
        this._silentToIdle = new ArrayList<Client>();
        this._busyToIdle = new ArrayList<Client>();
        this._busyToSilent = new ArrayList<Client>();
        this._terminal = context;
    }

    public void addOffToSilent(Client c) {
        this._offToSilent.add(c);
    }

    public void addSilentToIdle(Client c) {
        this._silentToIdle.add(c);
    }

    public void addBusyToIdle(Client c) {
        this._busyToIdle.add(c);
    }

    public void notify(NotificationType n) {
        List<Client> clients = null;
        switch (n) {
            case OFF_TO_SILENT -> {
                clients = this._offToSilent;
                this._offToSilent.clear();
            }

            case SILENT_TO_IDLE -> {
                clients = this._silentToIdle;
                this._silentToIdle.clear();
            }
            case BUSY_TO_IDLE -> {
                clients = this._busyToIdle;
                this._busyToIdle.clear();
            }
            case BUSY_TO_SILENT -> {
                clients = this._busyToSilent;
                this._busyToSilent.clear();
            }
        }
        for (Client c : clients) {
            c.notify(n+"|"+this._terminal.getId());
        }

    }
}