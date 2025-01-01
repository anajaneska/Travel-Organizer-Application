package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Expense;
import mk.ukim.finki.backendtravelorganizer.model.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense saveExpense(Expense expense);
    Expense addExpenseToTrip(Long tripId, Expense expense);
    List<Expense> getExpensesByTripId(Long tripId);
    void deleteExpense(Long expenseId);
    Expense editExpense(Long id, ExpenseDto dto);

}
