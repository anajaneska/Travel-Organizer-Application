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
    private LocalDate startDate;
    private LocalTime startTime;
    private Long trip;

    public ActivityDto() {
    }

    public ActivityDto(String name, String description, String location, LocalDate startDate, LocalTime startTime, Long trip) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
        this.trip = trip;
    }
}
