package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationSearchDto;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/transportations")
public class TransportationController {
    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportation> getTransportationById(@PathVariable Long id) {
        Transportation transportation = transportationService.getTransportationById(id);
        return ResponseEntity.ok(transportation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportation(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Transportation> addTransportationToTrip(@PathVariable Long tripId,
                                                                  @RequestBody TransportationDto dto) {
        Transportation saved = transportationService.addTransportationToTrip(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Transportation> editTransportation(@PathVariable Long id, @RequestBody TransportationCreateDto dto) {
//        return ResponseEntity.ok(transportationService.saveTransportation(id, dto));
//    }

//    @GetMapping("/trip/{tripId}")
//    public ResponseEntity<List<Transportation>> getTransportationByTripId(@PathVariable Long tripId) {
//        //List<Transportation> transportations = transportationService.getTransportationByTripId(tripId);
//        return ResponseEntity.ok(transportations);
//    }

}
