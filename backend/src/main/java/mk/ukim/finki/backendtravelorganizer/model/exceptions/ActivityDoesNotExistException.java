package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class ActivityDoesNotExistException extends RuntimeException{
    public ActivityDoesNotExistException() {
        super("Activity does not exist");
    }
}
