package mk.ukim.finki.backendtravelorganizer.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Expense;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TripDto {
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<AccommodationDto> accommodations;
    private List<ActivityDto> activities;
    private List<TransportationDto> transportations;
    private Double budget;
    private Double currentExpenses = 0.0;
    private List<ExpenseDto> expenses;

    public TripDto() {
    }

    public TripDto(String destination, LocalDate startDate, LocalDate endDate, List<AccommodationDto> accommodations, List<ActivityDto> activities, List<TransportationDto> transportations, Double budget, Double currentExpenses, List<ExpenseDto> expenses) {
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
