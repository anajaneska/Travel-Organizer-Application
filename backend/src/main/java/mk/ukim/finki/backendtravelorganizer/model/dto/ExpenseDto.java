package mk.ukim.finki.backendtravelorganizer.model.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ExpenseDto {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private Long trip;

    public ExpenseDto() {
    }

    public ExpenseDto(String description, Double amount, LocalDate date, Long trip) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.trip = trip;
    }
}
