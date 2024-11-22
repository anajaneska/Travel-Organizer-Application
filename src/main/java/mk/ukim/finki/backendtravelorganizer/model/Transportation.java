package mk.ukim.finki.backendtravelorganizer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.backendtravelorganizer.model.enumeration.TransportationType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportationType type;
    private String startLocation;
    private String destination;
    private String ticketInfo; // Path or identifier for ticket file
    private String route;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
