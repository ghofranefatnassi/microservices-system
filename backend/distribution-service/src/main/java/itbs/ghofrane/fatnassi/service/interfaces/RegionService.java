package itbs.ghofrane.fatnassi.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import itbs.ghofrane.fatnassi.entity.Region;

public interface RegionService {
	    ResponseEntity<Object> createRegion(Region region);
	    Optional<Region> getRegionById(Long id);
	    ResponseEntity<Object> updateRegion(Long id, Region region);
	    ResponseEntity<Object> deleteRegion(Long id);
	    List<Region> getAllRegions();
}
