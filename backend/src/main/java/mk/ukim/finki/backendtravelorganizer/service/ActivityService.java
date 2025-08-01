package mk.ukim.finki.backendtravelorganizer.service;


import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import java.util.List;

public interface ActivityService {
    Activity getActivityById(Long id);
    Activity saveActivity(Long tripId, Activity activity);
    void deleteActivity(Long id);
    List<Activity> getActivitiesByTripId(Long tripId);
    Activity editActivity(Long id, ActivityDto dto);
    Activity addActivityToTrip(Long tripId, ActivityDto dto);
}
