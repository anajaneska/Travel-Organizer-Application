package mk.ukim.finki.backendtravelorganizer.model.dto;

import java.time.LocalDate;

public record TransportationSearchDto(String startLocation, String destination, LocalDate departureDate, int wantedSeats) {
}
