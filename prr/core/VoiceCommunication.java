package prr.core;

public class VoiceCommunication extends InteractiveCommunication {
    //constructor
    public VoiceCommunication(int id, Terminal to, Terminal from) {
        super(id, to, from);
    }

    public String getType(){
        return  "VOICE";
    }
}
