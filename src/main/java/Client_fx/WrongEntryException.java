package Client_fx;

public class WrongEntryException extends IllegalArgumentException{
    public WrongEntryException(){
    }
    public WrongEntryException(String message){
        super(message);
    }
}
