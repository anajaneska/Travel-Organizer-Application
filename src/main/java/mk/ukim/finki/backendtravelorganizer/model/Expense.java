package mk.ukim.finki.backendtravelorganizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Expense(String description, Double amount, LocalDate date, Trip trip) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.trip = trip;
    }
}
