package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    List<Accommodation> findByTripId(Long tripId);

//    @Query(value = """
//    SELECT * FROM accommodation l
//    WHERE l.original_listing_id IS NULL         -- only LISTINGS
//      AND l.location          = :location
//      AND NOT EXISTS (        -- filter out listings with overlapping bookings
//          SELECT 1 FROM accommodation b
//          WHERE b.original_listing_id = l.id
//            AND b.check_in_date  < :checkOut
//            AND b.check_out_date > :checkIn
//      )
//""", nativeQuery = true)
//    List<Accommodation> findAvailable(
//            @Param("location") String location,
//            @Param("checkIn") LocalDate checkIn,
//            @Param("checkOut") LocalDate checkOut
//    );
}
