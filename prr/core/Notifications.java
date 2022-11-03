package prr.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;


public class Notifications implements Serializable{
    /** Serial number for serialization. */

    private static final long serialVersionUID = 202208091753L;
    
    private Map<String, Client> _clients;
    private ArrayList<Client> _offToSilent;
    private ArrayList<Client> _silentToIdle;
    private ArrayList<Client> _busyToIdle;
    private Terminal _notifyingTerminal;
    private NotificationType _type;
    
    public enum NotificationType{
        SMS,
        EMAIL,
        TEXTMESSAGE,
    }


    public Notifications(NotificationType type){
        this._clients = new TreeMap<String, Client>();
        this._offToSilent = new ArrayList <Client>();
        this._silentToIdle = new ArrayList <Client>();
        this._busyToIdle = new ArrayList <Client>();
        this._type = type;

    }
}