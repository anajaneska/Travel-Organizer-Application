package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import mk.ukim.finki.backendtravelorganizer.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/trips/{tripId}/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    @PostMapping
    public ResponseEntity<Activity> createActivity(@PathVariable Long tripId,
                                                   @RequestBody ActivityDto dto) {
        Activity savedActivity = activityService.addActivityToTrip(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getActivitiesForTrip(@PathVariable Long tripId) {
        List<Activity> activities = activityService.getActivitiesByTripId(tripId);
        return ResponseEntity.ok(activities);
    }
    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long activityId) {
        Activity activity = activityService.getActivityById(activityId);
        return ResponseEntity.ok(activity);
    }
    @PutMapping("/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long tripId,
                                                   @PathVariable Long activityId,
                                                   @RequestBody ActivityDto dto) {
        Activity updatedActivity = activityService.editActivity(activityId, dto);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }
}
