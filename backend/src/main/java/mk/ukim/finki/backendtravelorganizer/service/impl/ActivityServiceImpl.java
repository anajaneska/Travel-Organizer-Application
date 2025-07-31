package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.model.Activity;
import mk.ukim.finki.backendtravelorganizer.model.Trip;
import mk.ukim.finki.backendtravelorganizer.model.dto.ActivityDto;
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
    public List<Activity> getActivitiesByTripId(Long tripId) {
        return activityRepository.findByTripId(tripId);
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(ActivityDoesNotExistException::new);
    }

    @Override
    public Activity saveActivity(Long tripId,Activity activity) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(TripDoesNotExistException::new);
        activity.setTrip(trip);
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Activity editActivity(Long id, ActivityDto dto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(ActivityDoesNotExistException::new);

        Trip trip = tripRepository.findById(dto.getTrip())
                .orElseThrow(TripDoesNotExistException::new);

        activity.setName(dto.getName());
        activity.setDescription(dto.getDescription());
        activity.setLocation(dto.getLocation());
        activity.setStartTime(dto.getStartTime());
        activity.setEndTime(dto.getEndTime());
        activity.setCost(dto.getCost());
        activity.setTrip(trip);

        return activityRepository.save(activity);
    }
    public Activity addActivityToTrip(Long tripId, ActivityDto dto) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripDoesNotExistException());

        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setDescription(dto.getDescription());
        activity.setLocation(dto.getLocation());
        activity.setStartTime(dto.getStartTime());
        activity.setEndTime(dto.getEndTime());
        activity.setCost(dto.getCost());
        activity.setTrip(trip);

        // Save and return the newly created activity
        return activityRepository.save(activity);
    }

}
