package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpenseToTrip(Long tripId, Expense expense);
    List<Expense> getExpensesByTripId(Long tripId);
    void deleteExpense(Long expenseId);

}
