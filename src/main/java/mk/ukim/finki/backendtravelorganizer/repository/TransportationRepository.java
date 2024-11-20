package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationRepository extends JpaRepository<Transportation,Long> {
}
