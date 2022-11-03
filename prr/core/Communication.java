package prr.core;

import java.io.Serializable;
import prr.core.Client.ClientLevel;

abstract public class Communication implements Serializable{
     /** Serial number for serialization. */

    private static final long serialVersionUID = 202208091753L;
    private int _id;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private Type _type;
    private Status _status;
    private Terminal _terminalFrom;
    private Terminal _terminalTo;
    private int _units;
    private double _price;
    private int _size;


    public Communication(Type type, Status status ,int units, int id, double cost, Terminal terminalFrom, Terminal terminalTo, double price){
        _id = id;
        _cost = cost;
        _type = type;
        _status = status;
        _units = units;
        _terminalFrom = terminalFrom; 
        _terminalTo = terminalTo;
        _price = price;
        _size = 0;
    }

    //public void setDuration(){}

    //public abstract int getSize();

    public enum Type{
        VOICE,
        TEXT,
        VIDEO;
    }

    public enum Status{
        ONGOING,  //communication in progress 
        FINISHED; //communication ended
    }

    public Type getType(){
        return _type;
    }

    public Status getStatus(){
        return _status;
    }

    public boolean isOngoing(){
        return getStatus().compareTo(Status.ONGOING) == 0;
    }

    public int getIdCom(){
        return _id;
    }

    public Terminal getIdSender(){
        return _terminalFrom;
    }

    public Terminal getIdReceiver(){
        return _terminalTo;
    }

    public int getUnits(){
        return _units;
    }

    public double getPrice(){
        return _price;
    }

    public String toString(){
        //type|idCommunication|idSender|idReceiver|units|price|status
        StringBuilder sb = new StringBuilder("type");

        sb.append(getType()+"|");
        sb.append(getIdCom()+"|");
        sb.append(getIdSender()+"|");
        sb.append(getIdReceiver() +"|");
        sb.append(getUnits()+ "|");
        sb.append(getPrice()+"|");
        sb.append(getStatus());

        return sb.toString();
    }


    //protected double computeCost(ClientLevel client){
        
      //  return _cost*getSize();
    //}
}



