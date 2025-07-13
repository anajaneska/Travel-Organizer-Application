package mk.ukim.finki.backendtravelorganizer.model.dto;

import java.time.LocalDate;

public record AccommodationBookingDto(LocalDate checkInDate, LocalDate checkOutDate) {}

