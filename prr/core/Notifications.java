package prr.core;

import java.util.ArrayList;
import java.io.Serializable;

public class Notifications implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    private ArrayList<Client> _offToSilent;
    private ArrayList<Client> _silentToIdle;
    private ArrayList<Client> _busyToIdle;

    public enum NotificationType {
        OFF_TO_SILENT,
        SILENT_TO_IDLE,
        BUSY_TO_IDLE
    }

    public Notifications() {
        this._offToSilent = new ArrayList<Client>();
        this._silentToIdle = new ArrayList<Client>();
        this._busyToIdle = new ArrayList<Client>();
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
        switch (n) {
            case OFF_TO_SILENT -> {
                for (Client c : this._offToSilent) {
                    c.notifyOffToSilent();
                }
            }
            case SILENT_TO_IDLE -> {
                for (Client c : this._silentToIdle) {
                    c.notifySilentToIdle();
                }
            }
            case BUSY_TO_IDLE -> {
                for (Client c : this._busyToIdle) {
                    c.notifyBusyToIdle();
                }
            }
        }

    }
}