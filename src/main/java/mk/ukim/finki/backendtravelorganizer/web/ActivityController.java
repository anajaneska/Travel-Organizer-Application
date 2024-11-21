package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    @PostMapping
    public ResponseEntity<Activity> createActivity(@PathVariable Long tripId, @RequestBody Activity activity) {
        Activity savedActivity = activityService.addActivityToTrip(tripId, activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }

    // Get all activities for a trip
    @GetMapping
    public ResponseEntity<List<Activity>> getActivitiesForTrip(@PathVariable Long tripId) {
        List<Activity> activities = activityService.getActivitiesByTripId(tripId); // Get activities filtered by tripId
        return ResponseEntity.ok(activities);
    }

    // Update an existing activity
    @PutMapping("/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long tripId, @PathVariable Long activityId,
                                                   @RequestBody Activity activity) {
        Activity updatedActivity = activityService.getActivityById(activityId); // Modify if necessary
        updatedActivity.setName(activity.getName());
        updatedActivity.setDescription(activity.getDescription());
        updatedActivity.setDateTime(activity.getDateTime());
        activityService.saveActivity(updatedActivity);
        return ResponseEntity.ok(updatedActivity);
    }

    // Delete an activity
    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }

}
