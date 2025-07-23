package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransportationRepository extends JpaRepository<Transportation,Long> {
    List<Transportation> findByTripId(Long tripId);

//    @Query("""
//    SELECT l FROM Transportation l
//    WHERE l.originalListing IS NULL
//      AND l.startLocation = :startLocation
//      AND l.destination = :destination
//      AND l.departureDate = :departureDate
//      AND l.totalSeats >= :wantedSeats
//      AND (
//          l.totalSeats - COALESCE(
//              (SELECT SUM(b.seatsBooked) FROM Transportation b WHERE b.originalListing = l), 0
//          )
//      ) >= :wantedSeats
//""")
//    List<Transportation> findAvailableListingsWithEnoughSeats(
//            @Param("startLocation") String startLocation,
//            @Param("destination") String destination,
//            @Param("departureDate") LocalDate departureDate,
//            @Param("wantedSeats") int wantedSeats);

}
