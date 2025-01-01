package mk.ukim.finki.backendtravelorganizer.model.exceptions;

public class ExpenseDoesNotExistException extends RuntimeException{
    public ExpenseDoesNotExistException() {
        super("Expense does not exist");
    }
}
