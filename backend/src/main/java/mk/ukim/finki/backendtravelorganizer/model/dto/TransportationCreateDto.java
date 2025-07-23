package mk.ukim.finki.backendtravelorganizer.model.dto;

import mk.ukim.finki.backendtravelorganizer.model.enumeration.TransportationType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TransportationCreateDto(
        TransportationType type,
        String startLocation, String destination,
        LocalDateTime departureTime, LocalDateTime arrivalTime,
        double cost, int totalSeats) {}