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
    private List<Client> _offToIdle;
    private Terminal _terminal;

    public enum NotificationType {
        OFF_TO_SILENT,
        OFF_TO_IDLE,
        SILENT_TO_IDLE,
        BUSY_TO_IDLE
    }

    public Notifications(Terminal context) {
        this._offToSilent = new ArrayList<Client>();
        this._silentToIdle = new ArrayList<Client>();
        this._busyToIdle = new ArrayList<Client>();
        this._offToIdle = new ArrayList<Client>();
        this._terminal = context;
    }

    private void addOffToSilent(Client c) {
        this._offToSilent.add(c);
    }

    private void addSilentToIdle(Client c) {
        this._silentToIdle.add(c);
    }

    private void addFromBusy(Client c) {
        this._busyToIdle.add(c);
    }

    private void addOffToIdle(Client c) {
        this._offToIdle.add(c);
    }

    public void notifyOffToSilent() {
        for (Client c : this._offToSilent) {
            c.notify("O2S|" + this._terminal.getId());
        }
        this._offToSilent.clear();
    }

    public void notifySilentToIdle() {
        for (Client c : this._silentToIdle) {
            c.notify("S2I|" + this._terminal.getId());
        }
        this._silentToIdle.clear();
    }

    public void notifyBusyToIdle() {
        for (Client c : this._busyToIdle) {
            c.notify("B2I|" + this._terminal.getId());
        }
        this._busyToIdle.clear();
    }

    public void notifyOffToIdle() {
        for (Client c : this._offToIdle) {
            c.notify("O2I|" + this._terminal.getId());
        }
        this._offToIdle.clear();
    }

    public void addNotification(String actualState, String commType, Client c) {
        System.err.println("Actual state: " + actualState);
        switch (actualState) {
            case "OFF" -> { 
                if (commType.equals("TEXT")) 
                    this.addOffToSilent(c);
                else 
                    this.addOffToIdle(c);
                }
            case "SILENCE" -> this.addSilentToIdle(c);
            case "BUSY" -> this.addFromBusy(c);
        }

    }


}