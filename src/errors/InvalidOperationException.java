package errors;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(){
        super("Uno o più vincoli non sono validi");
    }
}
