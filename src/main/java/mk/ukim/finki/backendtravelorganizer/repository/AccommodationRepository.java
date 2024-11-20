package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
}
