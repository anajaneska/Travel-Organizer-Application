package mk.ukim.finki.backendtravelorganizer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record AccommodationBookingDto(
        @JsonProperty("checkIn") LocalDate checkInDate,
        @JsonProperty("checkOut") LocalDate checkOutDate,
        @JsonProperty("totalCost")Double totalCost
) {}
