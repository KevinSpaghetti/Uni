package agenzia;

public class NoResultsException extends Exception {

    public NoResultsException(String message){
        super(message);
    }

    public NoResultsException(String message, Throwable cause){
        super(message, cause);
    }

}
