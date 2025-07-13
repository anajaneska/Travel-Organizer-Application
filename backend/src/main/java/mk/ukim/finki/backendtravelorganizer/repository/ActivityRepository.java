package mk.ukim.finki.backendtravelorganizer.repository;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findByTripId(Long tripId);

    @Query("""
    SELECT a FROM Activity a
    WHERE (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:description IS NULL OR LOWER(a.description) LIKE LOWER(CONCAT('%', :description, '%')))
      AND (:location IS NULL OR LOWER(a.location) LIKE LOWER(CONCAT('%', :location, '%')))
      AND (:startDate IS NULL OR a.startDate = :startDate)
""")
    List<Activity> searchActivities(
            @Param("name") String name,
            @Param("description") String description,
            @Param("location") String location,
            @Param("startDate") LocalDate startDate
    );
}
