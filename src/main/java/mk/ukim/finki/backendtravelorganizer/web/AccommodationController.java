package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.service.AccommodationService;
import mk.ukim.finki.backendtravelorganizer.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final TripService tripService;

    public AccommodationController(AccommodationService accommodationService, TripService tripService) {
        this.accommodationService = accommodationService;
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationService.getAllAccommodations();
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Accommodation>> getAccommodationsByTrip(@PathVariable Long tripId) {
        List<Accommodation> accommodations = accommodationService.getAccommodationsByTripId(tripId);
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Accommodation accommodation = accommodationService.getAccommodationById(id);
        return ResponseEntity.ok(accommodation);
    }

    @PostMapping
    public ResponseEntity<Accommodation> createAccommodation(@RequestBody Accommodation accommodation) {
        Accommodation savedAccommodation = accommodationService.saveAccommodation(accommodation);
        return ResponseEntity.ok(savedAccommodation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Accommodation> addAccommodationToTrip(@PathVariable Long tripId, @RequestBody Accommodation accommodation) {
        Accommodation savedAccommodation = accommodationService.addAccommodationToTrip(tripId, accommodation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccommodation);  // 201 Created
    }
    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long id, @RequestBody AccommodationDto updatedAccommodationDto) {
        Accommodation editedAccommodation = accommodationService.editAccommodation(id, updatedAccommodationDto);
        return ResponseEntity.ok(editedAccommodation);
    }
}
