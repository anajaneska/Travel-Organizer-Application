package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;

import java.time.LocalDate;
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

    public TripDto() {
    }

    public TripDto(String destination, LocalDate startDate, LocalDate endDate, List<AccommodationDto> accommodations, List<ActivityDto> activities, List<TransportationDto> transportations, Double budget, Double currentExpenses) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodations = accommodations;
        this.activities = activities;
        this.transportations = transportations;
        this.budget = budget;
        this.currentExpenses = currentExpenses;
    }
}
