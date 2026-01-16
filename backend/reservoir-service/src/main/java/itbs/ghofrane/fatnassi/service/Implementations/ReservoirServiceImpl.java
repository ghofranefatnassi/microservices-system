package itbs.ghofrane.fatnassi.service.Implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import itbs.ghofrane.fatnassi.entity.Reservoir;
import itbs.ghofrane.fatnassi.repository.ReservoirRepository;
import itbs.ghofrane.fatnassi.service.interfaces.ReservoirService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservoirServiceImpl implements ReservoirService{
	  @Autowired
	    private ReservoirRepository reservoirRepository;

	    @Override
	    public ResponseEntity<Object> createReservoir(Reservoir reservoir) {
	        reservoirRepository.save(reservoir);
	        log.info("Réservoir ajouté avec succès");
	        return ResponseEntity.ok("Ajout avec succès");
	    }
	    
	    @Override
	    public List<Reservoir> getAllReservoirs() {
	        return reservoirRepository.findAll();
	    }

	    @Override
	    public Optional<Reservoir> getReservoirById(Long id) {
	        return reservoirRepository.findById(id);
	    }

	    @Override
	    public ResponseEntity<Object> updateReservoir(Long id, Reservoir reservoir) {

	        Reservoir existant = reservoirRepository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(
	                        HttpStatus.NOT_FOUND, "Réservoir non trouvé"));

	        existant.setNom(reservoir.getNom());
	        existant.setLocalisation(reservoir.getLocalisation());
	        existant.setCapacite(reservoir.getCapacite());
	        existant.setNiveauActuel(reservoir.getNiveauActuel());

	        reservoirRepository.save(existant);
	        log.info("Réservoir mis à jour avec succès");

	        return ResponseEntity.ok("Mise à jour réussie");
	    }
	    

	    @Override
	    public ResponseEntity<Object> updateNiveau(Long id, double niveauActuel) {

	        Reservoir reservoir = reservoirRepository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(
	                        HttpStatus.NOT_FOUND, "Réservoir non trouvé"));

	        reservoir.setNiveauActuel(niveauActuel);
	        reservoirRepository.save(reservoir);

	        log.info("Niveau du réservoir {} mis à jour", id);
	        return ResponseEntity.ok("Niveau mis à jour avec succès");
	    }

	    @Override
	    @Transactional
	    public ResponseEntity<Object> deleteReservoir(Long id) {

	        reservoirRepository.findById(id).ifPresentOrElse(
	                reservoir -> {
	                    reservoirRepository.deleteById(id);
	                    log.info("Réservoir supprimé avec succès");
	                },
	                () -> {
	                    log.warn("Réservoir avec id {} non trouvé", id);
	                    throw new ResponseStatusException(
	                            HttpStatus.NOT_FOUND, "Réservoir non trouvé");
	                }
	        );

	        return ResponseEntity.ok("Suppression réussie");
	    }
}
