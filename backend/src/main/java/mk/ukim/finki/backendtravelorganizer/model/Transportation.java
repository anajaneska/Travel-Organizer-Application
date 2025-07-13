package mk.ukim.finki.backendtravelorganizer.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.backendtravelorganizer.model.enumeration.TransportationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportationType type;
    private String startLocation;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate  arrivalDate;
    private LocalTime  arrivalTime;
    private Double cost;
    private Integer totalSeats;
    private Integer seatsBooked = 0;         // null for listing
    private String ticketInfo;           // null for listing

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_listing_id")
    private Transportation originalListing;

    @OneToMany(mappedBy = "originalListing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("originalListing")
    private List<Transportation> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip trip;

    public Transportation(TransportationType type, String startLocation, String destination, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, Double cost) {
        this.type = type;
        this.startLocation = startLocation;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
    }

    @JsonIgnore
    public int getSeatsAvailable() {
        int booked = bookings.stream()
                .mapToInt(t -> t.getSeatsBooked() != null ? t.getSeatsBooked() : 0)
                .sum();
        return totalSeats - booked;
    }
}
