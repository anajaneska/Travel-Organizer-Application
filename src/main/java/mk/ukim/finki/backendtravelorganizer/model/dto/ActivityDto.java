package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private Long trip;

    public ActivityDto() {
    }

    public ActivityDto(String name, String description, LocalDateTime dateTime, Long trip) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.trip = trip;
    }
}
