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
    private int _units;
    private double _price;
    private int _size;

    public Communication(int id, Terminal terminalTo,Terminal terminalfrom) {
        this._id = id;
        this._terminalTo = terminalTo;
        this._terminalFrom = terminalfrom;
    }

    public abstract int getSize();

    public boolean isOngoing() {
        return this._isOngoing;
    }

    public int getIdComm() {
        return _id;
    }

    public Terminal getIdSender() {
        return _terminalFrom;
    }

    public Terminal getIdReceiver() {
        return _terminalTo;
    }

    public int getUnits() {
        return _units;
    }

    public double getPrice() {
        return _price;
    }

    public String toString() {
        // type|idCommunication|idSender|idReceiver|units|price|status
        StringBuilder sb = new StringBuilder();
        sb.append(getIdComm() + "|");
        sb.append(getIdSender() + "|");
        sb.append(getIdReceiver() + "|");
        sb.append(getUnits() + "|");
        sb.append(getPrice() + "|");
        sb.append(isOngoing() ? "ONGOING" : "FINISHED");

        return sb.toString();
    }

    // protected double computeCost(ClientLevel client){

    // return _cost*getSize();
    // }
}
