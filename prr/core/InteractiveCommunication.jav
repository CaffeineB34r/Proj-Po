package prr.core;

/**
 * Class InteractiveCommunication implements a interactive communication.
 */
abstract public class InteractiveCommunication extends Communication{
    private int _duration;

    public InteractiveCommunication(Int duration){
        _duration = duration;
    }

    public Int getDuration(){
        return _duration;
    }
    
    abstract public int getSize();
} 

