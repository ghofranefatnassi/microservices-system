package itbs.ghofrane.fatnassi.entity;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data

public class Reservoir {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String localisation;
    private double capacite;
    private double niveauActuel;

    // Un réservoir peut avoir plusieurs mesures de niveau
    @OneToMany(mappedBy = "reservoir")
    private List<MesureNiveau> mesureNiveau = new ArrayList<>();

}
