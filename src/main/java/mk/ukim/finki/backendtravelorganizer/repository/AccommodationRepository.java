package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    List<Accommodation> findByTripId(Long tripId);
}
