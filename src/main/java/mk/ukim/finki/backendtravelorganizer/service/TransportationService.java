package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import mk.ukim.finki.backendtravelorganizer.model.dto.TransportationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransportationService {
    List<Transportation> getAllTransportations();
    Transportation getTransportationById(Long id);
    Transportation saveTransportation(Transportation transportation);
    void deleteTransportation(Long id);
    Transportation addTransportationToTrip(Long tripId, Transportation transportation);
    List<Transportation> getTransportationByTripId(Long tripId);
    Transportation uploadTicket(Long transportationId, MultipartFile file);
    Transportation editTransportation(Long id, TransportationDto dto);

}
