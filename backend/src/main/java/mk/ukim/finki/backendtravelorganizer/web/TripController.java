package mk.ukim.finki.backendtravelorganizer.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        return tripService.getTripById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.saveTrip(trip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip tripDetails) {
        return tripService.getTripById(id).map(existingTrip -> {
            existingTrip.setName(tripDetails.getName());
            existingTrip.setStartDate(tripDetails.getStartDate());
            existingTrip.setEndDate(tripDetails.getEndDate());
            existingTrip.setBudget(tripDetails.getBudget());
            Trip updatedTrip = tripService.saveTrip(existingTrip);
            return ResponseEntity.ok(updatedTrip);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        if (tripService.getTripById(id).isPresent()) {
            tripService.deleteTrip(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tripId}/budget-status")
    public ResponseEntity<String> getBudgetStatus(@PathVariable Long tripId) {
        Trip trip = tripService.getTripById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        boolean exceeded = trip.isBudgetExceeded();
        String message = exceeded ? "Budget exceeded!" : "Within budget.";
        return ResponseEntity.ok(message);
    }
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
