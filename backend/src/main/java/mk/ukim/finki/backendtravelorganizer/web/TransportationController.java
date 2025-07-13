package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationSearchDto;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;

import org.springframework.http.HttpHeaders;
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


    @GetMapping
    public List<Transportation> getAllTransportations(@RequestBody TransportationSearchDto dto) {
        return transportationService.getAllTransportations(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportation> getTransportationById(@PathVariable Long id) {
        Transportation transportation = transportationService.getTransportationById(id);
        return ResponseEntity.ok(transportation);
    }

    @PostMapping
    public ResponseEntity<Transportation> createTransportation(@RequestBody TransportationCreateDto dto) {
        Transportation savedTransportation = transportationService.createListing(dto);
        return ResponseEntity.ok(savedTransportation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportation(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/trip/{tripId}")
    public ResponseEntity<Transportation> addTransportationToTrip(@PathVariable Long id,
                                                                  @PathVariable Long tripId,
                                                                  @RequestBody TransportationBookingDto dto) {
        Transportation savedTransportation = transportationService.bookTransport(id, tripId, dto);
        return ResponseEntity.ok(savedTransportation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transportation> editTransportation(@PathVariable Long id, @RequestBody TransportationCreateDto dto) {
        return ResponseEntity.ok(transportationService.saveTransportation(id, dto));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Transportation>> getTransportationByTripId(@PathVariable Long tripId) {
        List<Transportation> transportations = transportationService.getTransportationByTripId(tripId);
        return ResponseEntity.ok(transportations);
    }
    @PostMapping("/{id}/upload-ticket")
    public ResponseEntity<Transportation> uploadTicket(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Transportation updatedTransportation = transportationService.uploadTicket(id, file);
        return ResponseEntity.ok(updatedTransportation);
    }
    @GetMapping("/{id}/ticket")
    public ResponseEntity<Resource> getTicket(@PathVariable Long id) {
        Transportation transportation = transportationService.getTransportationById(id);
        Path filePath = Paths.get(transportation.getTicketInfo());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found or not readable.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading file.", e);
        }
    }
}
