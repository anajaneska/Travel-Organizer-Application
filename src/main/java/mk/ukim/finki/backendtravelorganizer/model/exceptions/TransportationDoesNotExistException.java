package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class TransportationDoesNotExistException extends RuntimeException{
    public TransportationDoesNotExistException() {
        super("Transportation does not exist");
    }
}
