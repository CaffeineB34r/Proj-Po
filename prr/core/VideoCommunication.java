package prr.core;


public class VideoCommunication extends InteractiveCommunication {
    String _type= "VOICE";

    public VideoCommunication(int id, Terminal to, Terminal from) {
        super(id, to, from);
    }



}
