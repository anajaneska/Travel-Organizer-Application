package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Accommodation;
import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivitySearchDto;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.ActivityDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.model.exceptions.TripDoesNotExistException;
import mk.ukim.finki.backendtravelorganizer.repository.ActivityRepository;
import mk.ukim.finki.backendtravelorganizer.repository.TripRepository;
import mk.ukim.finki.backendtravelorganizer.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final TripRepository tripRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, TripRepository tripRepository) {
        this.activityRepository = activityRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Activity> getAllActivities(ActivitySearchDto dto) {
        return activityRepository.searchActivities(
                dto.name(), dto.description(), dto.location(), dto.startDate()
        );
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(ActivityDoesNotExistException::new);
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Activity addActivityToTrip(Long tripId, Activity activity) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(TripDoesNotExistException::new);
        trip.addActivity(activity);
        activityRepository.save(activity);
        return activity;
    }
    @Override
    public List<Activity> getActivitiesByTripId(Long tripId) {
        return activityRepository.findByTripId(tripId);
    }

    @Override
    public Activity editActivity(Long id, ActivityDto dto) {
        Trip trip = this.tripRepository.findById(dto.getTrip())
                .orElseThrow(TripDoesNotExistException::new);
        Activity activity = new Activity(dto.getName(), dto.getDescription(), dto.getLocation(), dto.getStartDate(), dto.getStartTime());
        this.activityRepository.save(activity);
        return activity;
    }
}
