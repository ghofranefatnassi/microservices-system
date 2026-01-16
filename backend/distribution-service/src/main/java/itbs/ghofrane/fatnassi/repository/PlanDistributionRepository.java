package itbs.ghofrane.fatnassi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itbs.ghofrane.fatnassi.entity.PlanDistribution;

public interface PlanDistributionRepository extends JpaRepository <PlanDistribution, Long>{
	List<PlanDistribution> findByReservoirId(Long reservoirId);
}
