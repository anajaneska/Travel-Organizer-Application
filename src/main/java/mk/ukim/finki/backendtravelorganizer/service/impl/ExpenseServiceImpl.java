package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Expense;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.ExpenseDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.ExpenseDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.repository.ExpenseRepository;
import mk.ukim.finki.backendtravelorganizer.repository.TripRepository;
import mk.ukim.finki.backendtravelorganizer.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final TripRepository tripRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, TripRepository tripRepository) {
        this.expenseRepository = expenseRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return this.expenseRepository.findById(id).orElseThrow(() -> new ExpenseDoesNotExistException());
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return this.expenseRepository.save(expense);
    }

    public Expense addExpenseToTrip(Long tripId, Expense expense) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new TripDoesNotExistException());
        trip.addExpense(expense);
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByTripId(Long tripId) {
        return expenseRepository.findByTripId(tripId);
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public Expense editExpense(Long id, ExpenseDto dto) {
        Trip trip = this.tripRepository.findById(dto.getTrip())
                .orElseThrow(()->new TripDoesNotExistException());
        Expense expense = new Expense(dto.getDescription(), dto.getAmount(), dto.getDate(),trip);
        this.expenseRepository.save(expense);
        return expense;
    }
}
