package itbs.ghofrane.fatnassi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itbs.ghofrane.fatnassi.entity.PlanDistribution;
import itbs.ghofrane.fatnassi.service.interfaces.PlanDistributionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/distributions/plans")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class PlanDistributionController {

	@Autowired
    private PlanDistributionService planService;

    //  Créer un plan de distribution
    @PostMapping
    public ResponseEntity<Object> createPlan(@RequestBody PlanDistribution plan) {
        return planService.createPlan(plan);
    }

    //  Récupérer un plan par son id
    @GetMapping("/{id}")
    public Optional<PlanDistribution> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    //  Mettre à jour un plan
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable Long id, @RequestBody PlanDistribution plan) {
        return planService.updatePlan(id, plan);
    }

    //  Supprimer un plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlan(@PathVariable Long id) {
        return planService.deletePlan(id);
    }

    //  Changer le statut d’un plan
    @PutMapping("/{id}/changer-statut")
    public ResponseEntity<Object> changeStatut(@PathVariable Long id) {
        return planService.changeStatut(id);
    }

    //  Liste des plans d’un réservoir
    @GetMapping("/reservoir/{reservoirId}")
    public List<PlanDistribution> getPlansByReservoir(@PathVariable Long reservoirId) {
        return planService.getPlansByReservoirId(reservoirId);
    }

}
