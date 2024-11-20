package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.repository.TransportationRepository;
import mk.ukim.finki.backendtravelorganizer.service.TransportationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.backendtravelorganizer.model.Transportation;

import java.util.List;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;

    public TransportationServiceImpl(TransportationRepository transportationRepository) {
        this.transportationRepository = transportationRepository;
    }

    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }

    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow(() -> new RuntimeException("Transportation not found"));
    }

    public Transportation saveTransportation(Transportation transportation) {
        return transportationRepository.save(transportation);
    }

    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }
}
