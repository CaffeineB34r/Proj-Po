package prr.core;

/**
 * Class TextCommunication implements a text communication.
 */
public class TextCommunication extends Communication {
    private String _message;


    public TextCommunication(int id, Terminal to,Terminal from,String message){
        super(id,to,from);
        this._message = message;
    }

    public int getSize(){
        return this._message.length();
    }

    public String getMessage(){
        return _message;
    }

    public String getType(){
        return "TEXT";
    }

}