package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class TripDoesNotExistException extends RuntimeException {
    public TripDoesNotExistException() {
        super("Trip does not exist");
    }
}
