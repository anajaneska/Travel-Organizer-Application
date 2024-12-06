package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Expense;
import mk.ukim.finki.backendtravelorganizer.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping()
    public ResponseEntity<Expense> addExpenseToTrip(@PathVariable Long tripId, @RequestBody Expense expense) {
        Expense savedExpense = expenseService.addExpenseToTrip(tripId, expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping()
    public ResponseEntity<List<Expense>> getExpensesByTrip(@PathVariable Long tripId) {
        List<Expense> expenses = expenseService.getExpensesByTripId(tripId);
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }
}
