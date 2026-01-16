package itbs.ghofrane.fatnassi.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import itbs.ghofrane.fatnassi.entity.Region;
import itbs.ghofrane.fatnassi.repository.RegionRepository;
import itbs.ghofrane.fatnassi.service.interfaces.RegionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegionServiceImpl implements RegionService{

	 @Autowired
	    private RegionRepository repository;
	 @Override
	    public ResponseEntity<Object> createRegion(Region region) {
	        repository.save(region);
	        log.info("Region '{}' créée avec succès", region.getNom());
	        return ResponseEntity.ok("Region créée avec succès");
	    }

	    @Override
	    public Optional<Region> getRegionById(Long id) {
	        Optional<Region> region = repository.findById(id);
	        if (region.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Region non trouvée");
	        }
	        return region;
	    }

	    @Override
	    public ResponseEntity<Object> updateRegion(Long id, Region region) {
	        Region existante = repository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region non trouvée"));

	        existante.setNom(region.getNom());
	        existante.setSuperficieAgricole(region.getSuperficieAgricole());
	        existante.setBesoinEau(region.getBesoinEau());

	        repository.save(existante);
	        log.info("Region '{}' mise à jour avec succès", id);
	        return ResponseEntity.ok("Region mise à jour avec succès");
	    }

	    @Override
	    public ResponseEntity<Object> deleteRegion(Long id) {
	        if (!repository.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Region non trouvée");
	        }
	        repository.deleteById(id);
	        log.info("Region '{}' supprimée", id);
	        return ResponseEntity.ok("Region supprimée avec succès");
	    }

	    @Override
	    public List<Region> getAllRegions() {
	        return repository.findAll();
	    }
}
