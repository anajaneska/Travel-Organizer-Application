package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class AccommodationDoesNotExistException extends RuntimeException {
    public AccommodationDoesNotExistException() {
        super("Accommodation does not exist");
    }
}
