package mk.ukim.finki.backendtravelorganizer.model.dto;

import java.time.LocalDate;

public record AccommodationSearchDto(String location, LocalDate checkInDate, LocalDate checkOutDate) {}

