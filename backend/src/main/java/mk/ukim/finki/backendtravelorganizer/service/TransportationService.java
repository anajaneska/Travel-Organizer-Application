package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationBookingDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationCreateDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationSearchDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface TransportationService {
    Transportation getTransportationById(Long id);
    Transportation saveTransportation(Long id, TransportationDto dto);
    Transportation editTransportation(Long id, TransportationDto dto);
    void deleteTransportation(Long id);
    List<Transportation> getTransportationByTripId(Long tripId);
    Transportation addTransportationToTrip(Long tripId, TransportationDto dto);
}
