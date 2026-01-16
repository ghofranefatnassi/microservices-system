package itbs.ghofrane.fatnassi.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import itbs.ghofrane.fatnassi.entity.PlanDistribution;
import itbs.ghofrane.fatnassi.entity.StatutDistribution;
import itbs.ghofrane.fatnassi.feign.ReservoirFeign;
import itbs.ghofrane.fatnassi.repository.PlanDistributionRepository;
import itbs.ghofrane.fatnassi.service.interfaces.PlanDistributionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanDistributionServiceImpl implements PlanDistributionService{

	 @Autowired
	    private PlanDistributionRepository repository;

	    @Autowired
	    private ReservoirFeign reservoirFeign;

	    @Override
	    public ResponseEntity<Object> createPlan(PlanDistribution plan) {
	        // Vérifier volume disponible via Feign
	        Double volumeDisponible = reservoirFeign.getAvailableVolume(plan.getReservoirId());
	        if (volumeDisponible == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservoir non trouvé");
	        }

	        // Déterminer le statut initial
	        if (volumeDisponible >= plan.getVolumeAttribue()) {
	            plan.setStatutDistribution(StatutDistribution.PLANIFIE);
	        } else {
	            plan.setStatutDistribution(StatutDistribution.ANNULE);
	        }

	        repository.save(plan);
	        log.info("PlanDistribution créé pour le réservoir {} avec statut {}", plan.getReservoirId(), plan.getStatutDistribution());
	        return ResponseEntity.ok("PlanDistribution créé avec succès");
	    }

	    @Override
	    public Optional<PlanDistribution> getPlanById(Long id) {
	        Optional<PlanDistribution> plan = repository.findById(id);
	        if (plan.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanDistribution non trouvé");
	        }
	        return plan;
	    }

	    @Override
	    public ResponseEntity<Object> updatePlan(Long id, PlanDistribution plan) {
	        PlanDistribution existante = repository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanDistribution non trouvé"));

	        existante.setVolumeAttribue(plan.getVolumeAttribue());
	        existante.setDatePlanifiee(plan.getDatePlanifiee());
	        existante.setStatutDistribution(plan.getStatutDistribution());
	        existante.setReservoirId(plan.getReservoirId());

	        repository.save(existante);
	        log.info("PlanDistribution {} mis à jour", id);
	        return ResponseEntity.ok("PlanDistribution mis à jour avec succès");
	    }

	    @Override
	    public ResponseEntity<Object> deletePlan(Long id) {
	        if (!repository.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanDistribution non trouvé");
	        }

	        repository.deleteById(id);
	        log.info("PlanDistribution {} supprimé", id);
	        return ResponseEntity.ok("PlanDistribution supprimé avec succès");
	    }

	    @Override
	    public ResponseEntity<Object> changeStatut(Long id) {
	        PlanDistribution plan = repository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanDistribution non trouvé"));

	        // Cycle simple : PLANIFIE -> EN_COURS -> TERMINE -> ANNULE -> PLANIFIE
	        switch (plan.getStatutDistribution()) {
	            case PLANIFIE -> {
	                plan.setStatutDistribution(StatutDistribution.EN_COURS);
	            }
	            case EN_COURS -> {
	                plan.setStatutDistribution(StatutDistribution.TERMINE);
	            }
	            case TERMINE -> {
	                plan.setStatutDistribution(StatutDistribution.ANNULE);
	            }
	            case ANNULE -> {
	                plan.setStatutDistribution(StatutDistribution.PLANIFIE);
	            }
	        }

	        repository.save(plan);
	        log.info("PlanDistribution {} changé de statut à {}", id, plan.getStatutDistribution());
	        return ResponseEntity.ok("Statut du PlanDistribution changé avec succès");
	    }

	    @Override
	    public List<PlanDistribution> getPlansByReservoirId(Long reservoirId) {
	        return repository.findByReservoirId(reservoirId);
	    }
}
