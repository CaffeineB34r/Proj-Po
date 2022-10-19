package prr.core;


import java.util.ArrayList;
import java.io.Serializable;

public class Client implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
 
    private final String _key;
    private final String _name;
    private final int _taxNumber;
    private ClientLevel _level;
    private boolean _receiveNotifications;
    private ArrayList <Terminal> _terminals;
    private int _payments;
    private int _debts;

    public enum ClientLevel {
        NORMAL,
        GOLD,
        PLATINUM,
    } 

    public Client(String _key,String name,int taxId){
        this._key = _key;
        this._name = name;
        this._taxNumber = taxId;
        this._payments = 0;
        this._debts = 0;
        this._terminals = new ArrayList <Terminal>();
        this._level = ClientLevel.NORMAL;
        this._receiveNotifications = true;
    }

    public boolean disableReceiveNotifications(){
        if (getReceiveNotifications()){
            this._receiveNotifications = false;
            return true;
        }
        return false;
    }

    public boolean enableRecieveNotifications(){
        if (!getReceiveNotifications()){
            this._receiveNotifications = true;
            return true;
        }
        return false;
    }

    //Getters
    public String getKey() {
        return _key;
    }
    public String getName() {
        return _name;
    }
    public int getTaxNumber() {
        return _taxNumber;
    }
    public ClientLevel getLevel() {
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

    public String toString(){
        //CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
        StringBuilder sb = new StringBuilder("CLIENT|");

        sb.append(getKey()+"|");
        sb.append(getName()+"|");
        sb.append(getTaxNumber()+"|");
        sb.append(getLevel()+"|");
        sb.append((getReceiveNotifications())? "YES":"NO" +"|");
        sb.append(getTerminalsSize()+"|");
        sb.append(getPayments()+"|");
        sb.append(getDebts());

        return sb.toString();
    }
}
