package mk.ukim.finki.backendtravelorganizer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Accommodation> accommodations = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transportation> transportations = new ArrayList<>();

    private Double budget;
    private Double currentExpenses = 0.0;

    public void addAccommodation(Accommodation accommodation) {
        accommodation.setTrip(this);
        this.accommodations.add(accommodation);
        this.currentExpenses += (accommodation.getTotalCost() != null) ? accommodation.getTotalCost() : 0.0;

    }
    public void addActivity(Activity activity){
        activity.setTrip(this);
        this.activities.add(activity);
        this.currentExpenses += (activity.getCost() != null) ? activity.getCost() : 0.0;
    }
    public void addTransportation(Transportation transportation){
        transportation.setTrip(this);
        this.transportations.add(transportation);
        this.currentExpenses += (transportation.getCost() != null) ? transportation.getCost() : 0.0;
    }

    public boolean isBudgetExceeded() {
        return currentExpenses > budget;
    }
    public void recalculateTotal() {
        this.currentExpenses = 0.0;

        for (Accommodation a : accommodations)
            this.currentExpenses += a.getTotalCost() != null ? a.getTotalCost() : 0.0;

        for (Activity act : activities)
            this.currentExpenses += act.getCost() != null ? act.getCost() : 0.0;

        for (Transportation t : transportations)
            this.currentExpenses += t.getCost() != null ? t.getCost() : 0.0;
    }

    public Trip(String name, LocalDate startDate, LocalDate endDate, Double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }
}
