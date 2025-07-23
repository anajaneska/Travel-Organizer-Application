package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import mk.ukim.finki.backendtravelorganizer.service.ActivityService;
import mk.ukim.finki.backendtravelorganizer.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;
    private final TripService tripService;

    public ActivityController(ActivityService activityService, TripService tripService) {
        this.activityService = activityService;
        this.tripService = tripService;
    }
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Activity> addActivityToTrip(@PathVariable Long tripId,
                                                   @RequestBody ActivityDto dto) {
        Activity savedActivity = activityService.addActivityToTrip(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Activity>> getActivitiesForTrip(@PathVariable Long tripId) {
        List<Activity> activities = activityService.getActivitiesByTripId(tripId);
        return ResponseEntity.ok(activities);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id,
                                                   @RequestBody ActivityDto dto) {
        Activity updatedActivity = activityService.editActivity(id, dto);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
