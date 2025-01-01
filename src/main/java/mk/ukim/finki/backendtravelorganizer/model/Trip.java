package mk.ukim.finki.backendtravelorganizer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Accommodation> accommodations = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transportation> transportations = new ArrayList<>();

    private Double budget;
    private Double currentExpenses = 0.0;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Expense> expenses = new ArrayList<>();

    public void addAccommodation(Accommodation accommodation) {
        accommodation.setTrip(this);
        this.accommodations.add(accommodation);
    }
    public void addActivity(Activity activity){
        activity.setTrip(this);
        this.activities.add(activity);
    }
    public void addTransportation(Transportation transportation){
        transportation.setTrip(this);
        this.transportations.add(transportation);
    }
    public void addExpense(Expense expense) {
        expense.setTrip(this);
        this.expenses.add(expense);
        this.currentExpenses += expense.getAmount();
    }

    public boolean isBudgetExceeded() {
        return currentExpenses > budget;
    }

    public Trip(String destination, LocalDate startDate, LocalDate endDate, List<Accommodation> accommodations, List<Activity> activities, List<Transportation> transportations, Double budget, Double currentExpenses, List<Expense> expenses) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodations = accommodations;
        this.activities = activities;
        this.transportations = transportations;
        this.budget = budget;
        this.currentExpenses = currentExpenses;
        this.expenses = expenses;
    }
}
