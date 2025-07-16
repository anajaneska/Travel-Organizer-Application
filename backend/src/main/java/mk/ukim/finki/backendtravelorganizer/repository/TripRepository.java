package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByUserUsername(String username);
}
