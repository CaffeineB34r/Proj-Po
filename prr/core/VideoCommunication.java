package prr.core;


public class VideoCommunication extends InteractiveCommunication {

    public VideoCommunication(int id, Terminal to, Terminal from) {
        super(id, to, from);
    }

    public String getType(){
        return  "VOICE";
    }


}
