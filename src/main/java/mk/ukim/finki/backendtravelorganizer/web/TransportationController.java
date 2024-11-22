package mk.ukim.finki.backendtravelorganizer.web;



import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportations")
public class TransportationController {
    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }


    @GetMapping
    public List<Transportation> getAllTransportations() {
        return transportationService.getAllTransportations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportation> getTransportationById(@PathVariable Long id) {
        Transportation transportation = transportationService.getTransportationById(id);
        return ResponseEntity.ok(transportation);
    }

    @PostMapping
    public ResponseEntity<Transportation> createTransportation(@RequestBody Transportation transportation) {
        Transportation savedTransportation = transportationService.saveTransportation(transportation);
        return ResponseEntity.ok(savedTransportation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportation(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Transportation> addTransportationToTrip(
            @PathVariable Long tripId,
            @RequestBody Transportation transportation) {
        Transportation savedTransportation = transportationService.addTransportationToTrip(tripId, transportation);
        return ResponseEntity.ok(savedTransportation);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Transportation>> getTransportationByTripId(@PathVariable Long tripId) {
        List<Transportation> transportations = transportationService.getTransportationByTripId(tripId);
        return ResponseEntity.ok(transportations);
    }
}
