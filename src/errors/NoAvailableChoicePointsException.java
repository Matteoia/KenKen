package errors;

public class NoAvailableChoicePointsException extends RuntimeException{
    public NoAvailableChoicePointsException(){
        super("Nessun punto di scelta disponibile");
    }
}
