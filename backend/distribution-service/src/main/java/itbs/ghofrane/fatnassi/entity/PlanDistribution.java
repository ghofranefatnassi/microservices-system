package itbs.ghofrane.fatnassi.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PlanDistribution {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
    private StatutDistribution statutDistribution;
	private Double volumeAttribue;
	private LocalDate datePlanifiee;
	 @ManyToOne
	 @JoinColumn(name = "region_id")
	 private Region region;
	 private Long reservoirId;
}
