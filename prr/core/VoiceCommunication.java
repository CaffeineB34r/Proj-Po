package prr.core;

public class VoiceCommunication extends InteractiveCommunication {
    //similar to VideoCommunication
    String _type= "VOICE";
    //constructor
    public VoiceCommunication(int id, Terminal to, Terminal from) {
        super(id, to, from);
    }

    
}
