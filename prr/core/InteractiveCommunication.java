package prr.core;

/**
 * Class InteractiveCommunication implements a interactive communication.
 */
abstract public class InteractiveCommunication extends Communication {
    private int _duration;
    private boolean _isOngoing;

    public InteractiveCommunication(int id, Terminal to, Terminal from) {
        super(id, to, from);
        this._isOngoing = true;
    }


    public boolean isOngoing() {
        return this._isOngoing;
    }

    @Override
    public int getSize() {
        return _duration;
    }

    public double end(int size) {
        _duration = size;
        computeCost();
        double cost = getCost(); 
        this.getSender().endOngoingCommunication(_duration);
        this.getReceiver().endOngoingCommunication(_duration);
        this._isOngoing = false;
        return cost;
    }
}
