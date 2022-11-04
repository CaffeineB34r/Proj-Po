package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private int _id;
    private boolean _isPaid;
    private double _cost;
    private Terminal _terminalFrom;
    private Terminal _terminalTo;

    public Communication(int id, Terminal terminalTo, Terminal terminalfrom) {
        this._id = id;
        this._terminalTo = terminalTo;
        this._terminalFrom = terminalfrom;
        this._isPaid = false;
    }

    public abstract int getSize();

    public abstract String getType();

    private void pay() {
        this._isPaid = true;
    }

    public boolean isOngoing() {
        return false;
    }

    public int getIdComm() {
        return this._id;
    }

    public Terminal getSender() {
        return this._terminalFrom;
    }

    public Terminal getReceiver() {
        return this._terminalTo;
    }

    public void computeCost() {

        this._cost = this._terminalTo.getOwner().computeCost(this);
    }

    public double getCost() {
        return this._cost;
    }

    public String toString() {
        // type|idCommunication|idSender|idReceiver|units|price|status
        StringBuilder sb = new StringBuilder(getType() + "|");
        sb.append(getIdComm() + "|");
        sb.append(getSender().getId() + "|");
        sb.append(getReceiver().getId() + "|");
        sb.append(getSize() + "|");
        sb.append(Math.round(getCost()) + "|");
        sb.append(isOngoing() ? "ONGOING" : "FINISHED");

        return sb.toString();
    }

}