package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccommodationDto {
    private Long id;
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalCost;
    private Long trip;

    public AccommodationDto() {
    }

    public AccommodationDto(String location, LocalDate checkInDate, LocalDate checkOutDate, double totalCost, Long trip) {
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.trip = trip;
    }
}
