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

    public Communication(int id, double cost){
        _id = id;
        _cost = cost;
    }

    public int getSize(){
        
    }

    protected double computeCost(ClientLevel client){
        
        return _cost*getSize();
    }
}



