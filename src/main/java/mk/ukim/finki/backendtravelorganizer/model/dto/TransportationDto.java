package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;
import mk.ukim.finki.backendtravelorganizer.model.enumeration.TransportationType;

import java.time.LocalDateTime;

@Data
public class TransportationDto {
    private Long id;
    private TransportationType type;
    private String startLocation;
    private String destination;
    private String ticketInfo;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double cost;
    private Long trip;
}
