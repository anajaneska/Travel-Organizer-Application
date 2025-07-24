package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.service.AmadeusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amadeus")
@CrossOrigin(origins = "*") // adjust if needed
public class AmadeusController {

    private final AmadeusService amadeusService;

    public AmadeusController(AmadeusService amadeusService) {
        this.amadeusService = amadeusService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> getHotels() {
        try {
            return ResponseEntity.ok(amadeusService.getRandomHotels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching hotels");
        }
    }
}

