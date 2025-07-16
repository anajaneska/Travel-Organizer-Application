package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripCreateDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double budget;
}
