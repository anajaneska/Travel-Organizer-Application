package mk.ukim.finki.backendtravelorganizer.model.dto;

import java.time.LocalDate;

public record ActivitySearchDto(
        String name,
        String description,
        String location,
        LocalDate startDate
) {}