package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.AccommodationSearchDto;
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

    @GetMapping
    public ResponseEntity<List<Accommodation>> getAllAccommodations(@RequestBody AccommodationSearchDto dto) {
        return ResponseEntity.ok(accommodationService.getAllAccommodations(dto.location(), dto.checkInDate(), dto.checkOutDate()));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Accommodation>> getAllAvailableAccommodations(){
        List<Accommodation> accommodations = accommodationService.getAllAvailableAccommodations();
        return ResponseEntity.ok(accommodations);
    }

    @PostMapping
    public ResponseEntity<Accommodation> createListing(@RequestBody AccommodationCreateDto dto) {
        return ResponseEntity.ok(accommodationService.createListing(dto.location(), dto.costPerNight()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> editListing(@PathVariable Long id, @RequestBody AccommodationCreateDto dto) {
        return ResponseEntity.ok(accommodationService.editListing(id, dto.location(), dto.costPerNight()));
    }

    @PostMapping("{id}/trip/{tripId}")
    public ResponseEntity<Accommodation> addAccommodationToTrip(@PathVariable Long id, @PathVariable Long tripId, @RequestBody AccommodationBookingDto dto) {
        Accommodation savedAccommodation = accommodationService.addAccommodationToTrip(id, tripId, dto.checkInDate(), dto.checkOutDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccommodation);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

}
