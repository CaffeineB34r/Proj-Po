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

    public void addBusyToSilent(Client c) {
        this._busyToSilent.add(c);
    }

    public void notifyOffToSilent() {
        for (Client c : this._offToSilent) {
            c.notify("OFF_TO_SILENT|"+this._terminal.getId());
        }
        this._offToSilent.clear();
    }

    public void notifySilentToIdle() {
        for (Client c : this._silentToIdle) {
            c.notify("SILENT_TO_IDLE|"+this._terminal.getId());
        }
        this._silentToIdle.clear();
    }

    public void notifyBusyToIdle() {
        for (Client c : this._busyToIdle) {
            c.notify("BUSY_TO_IDLE|"+this._terminal.getId());
        }
        this._busyToIdle.clear();
    }

    public void notifyBusyToSilent() {
        for (Client c : this._busyToSilent) {
            c.notify("BUSY_TO_SILENT|"+this._terminal.getId());
        }
        this._busyToSilent.clear();
    }
}