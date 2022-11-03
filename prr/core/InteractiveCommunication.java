package prr.core;

/**
 * Class InteractiveCommunication implements a interactive communication.
 */
abstract public class InteractiveCommunication extends Communication{
    private int _duration;

    public InteractiveCommunication(int id, Terminal to, Terminal from){
        super(id,to,from);
    }

    @Override
    public int getSize(){
        return _duration;
    }
} 

