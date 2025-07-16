package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException() {
        super("Username does not exist");
    }

}
