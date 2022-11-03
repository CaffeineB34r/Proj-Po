package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private int _id;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private Terminal _terminalFrom;
    private Terminal _terminalTo;



    public Communication(int id, Terminal terminalTo,Terminal terminalfrom) {
        this._id = id;
        this._terminalTo = terminalTo;
        this._terminalFrom = terminalfrom;
        this._isPaid = false;
    }

    public abstract int getSize();
    public abstract String getType();

    private void pay(){
        this._isPaid = true;
    }

    public boolean isOngoing() {
        return this._isOngoing;
    }

    public int getIdComm() {
        return this._id;
    }

    public Terminal getIdSender() {
        return this._terminalFrom;
    }

    public Terminal getIdReceiver() {
        return this._terminalTo;
    }

    public double getCost(){
        return this._cost;
    }

    public String toString() {
        // type|idCommunication|idSender|idReceiver|units|price|status
        StringBuilder sb = new StringBuilder(getType() +"|");
        sb.append(getIdComm() + "|");
        sb.append(getIdSender() + "|");
        sb.append(getIdReceiver() + "|");
        sb.append(getSize() + "|");
        sb.append(getCost() + "|");
        sb.append(isOngoing() ? "ONGOING" : "FINISHED");

        return sb.toString();
    }

}