package itbs.ghofrane.fatnassi.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import itbs.ghofrane.fatnassi.entity.PlanDistribution;

public interface PlanDistributionService {
	    ResponseEntity<Object> createPlan(PlanDistribution plan);
	    Optional<PlanDistribution> getPlanById(Long id);
	    ResponseEntity<Object> updatePlan(Long id, PlanDistribution plan);
	    ResponseEntity<Object> deletePlan(Long id);
	    ResponseEntity<Object> changeStatut(Long id);
	    List<PlanDistribution> getPlansByReservoirId(Long reservoirId);
}
