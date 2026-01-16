package itbs.ghofrane.fatnassi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class MesureNiveau {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double volume;
    private LocalDateTime dateMesure;

    // Plusieurs mesures appartiennent à un seul réservoir
    @ManyToOne
    @JoinColumn(name = "reservoir_id")
    private Reservoir reservoir;
}
