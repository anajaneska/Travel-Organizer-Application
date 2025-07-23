package mk.ukim.finki.backendtravelorganizer.service;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivitySearchDto;

import java.util.List;

public interface ActivityService {
    Activity getActivityById(Long id);
    Activity saveActivity(Long tripId, Activity activity);
    void deleteActivity(Long id);
    List<Activity> getActivitiesByTripId(Long tripId);
    Activity editActivity(Long id, ActivityDto dto);
}
