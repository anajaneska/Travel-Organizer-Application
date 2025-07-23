package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivitySearchDto;

import java.util.List;

public interface ActivityService {
    //List<Activity> getAllActivities(ActivitySearchDto dto);
    Activity getActivityById(Long id);
    Activity saveActivity(Activity activity);
    void deleteActivity(Long id);
    Activity addActivityToTrip(Long tripId, Activity activity);
    List<Activity> getActivitiesByTripId(Long tripId);
    Activity editActivity(Long id, ActivityDto dto);
}
