package prr.core;

/**
 * Class TextCommunication implements a text communication.
 */
public class TextCommunication extends Communication {
    private String _message;

    public TextCommunication (String message){
        _message = message;
    }
    public int getSize(){

    }

public String getMessage(){
    return _message;
    }

}