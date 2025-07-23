package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationDto;
import mk.ukim.finki.backendtravelorganizer.service.AccommodationService;
import mk.ukim.finki.backendtravelorganizer.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final TripService tripService;

    public AccommodationController(AccommodationService accommodationService, TripService tripService) {
        this.accommodationService = accommodationService;
        this.tripService = tripService;
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Accommodation>> getAccommodationsForTrip(@PathVariable Long tripId){
        List<Accommodation> accommodations = accommodationService.getAccommodationsByTripId(tripId);
        return ResponseEntity.ok(accommodations);
    }
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Accommodation> addAccommodationToTrip(@PathVariable Long tripId,
                                                                @RequestBody AccommodationDto dto) {
        Accommodation saved = accommodationService.addAccommodationToTrip(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable Long id,
                                                             @RequestBody AccommodationDto dto) {
        Accommodation updated = accommodationService.editAccommodation(id, dto);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Accommodation accommodation = accommodationService.getAccommodationById(id);
        return ResponseEntity.ok(accommodation);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }
}
