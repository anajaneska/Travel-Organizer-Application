package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;

import java.util.List;

public interface TransportationService {
    List<Transportation> getAllTransportations();
    Transportation getTransportationById(Long id);
    Transportation saveTransportation(Transportation transportation);
    void deleteTransportation(Long id);
    Transportation addTransportationToTrip(Long tripId, Transportation transportation);
    List<Transportation> getTransportationByTripId(Long tripId);

}
