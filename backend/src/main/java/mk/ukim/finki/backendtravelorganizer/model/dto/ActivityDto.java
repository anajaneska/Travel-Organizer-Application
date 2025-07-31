package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long trip;
    private Double cost;

    public ActivityDto() {
    }

    public ActivityDto(String name, String description, String location, LocalDateTime startTime,LocalDateTime endTime, Long trip, Double cost) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trip = trip;
        this.cost = cost;
    }
}
