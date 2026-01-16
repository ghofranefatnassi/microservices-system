package itbs.ghofrane.fatnassi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Region {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nom;
	private Double superficieAgricole;
	private Double besoinEau;
	
	@OneToMany(mappedBy = "region")
	private List<PlanDistribution> plantDistribution = new ArrayList<>();

}
