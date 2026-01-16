package itbs.ghofrane.fatnassi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservoir-service")

public interface ReservoirFeign {
	@GetMapping("/api/reservoirs/{id}/available-volume") 
    Double getAvailableVolume(@PathVariable Long id);
}
