package mk.ukim.finki.backendtravelorganizer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @Column(name = "cost_per_night")
    private double costPerNight; // accommodation listing

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Column(name = "total_cost")
    private double totalCost;  // per booking

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_listing_id")
    private Accommodation originalListing;

    @OneToMany(mappedBy = "originalListing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("originalListing")
    private List<Accommodation> bookings;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip trip;


    // constructor for creating listings without booking
    public Accommodation(String location, double costPerNight, String imageUrl) {
        this.location = location;
        this.costPerNight = costPerNight;
        this.imageUrl = imageUrl;
    }

    // booking constructor
    public Accommodation(String location, double costPerNight, LocalDate checkInDate, LocalDate checkOutDate, Trip trip, String imageUrl) {
        this.location = location;
        this.costPerNight = costPerNight;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.trip = trip;
        this.totalCost = ChronoUnit.DAYS.between(checkInDate, checkOutDate) * costPerNight;
        this.imageUrl = imageUrl;
    }
}
