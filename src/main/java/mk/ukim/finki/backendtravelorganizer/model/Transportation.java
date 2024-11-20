package mk.ukim.finki.backendtravelorganizer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.backendtravelorganizer.model.enumeration.TransportationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportationType type;
    private String schedule;
    private String route;
    private String ticketInfo;
}
