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

import itbs.ghofrane.fatnassi.entity.Region;
import itbs.ghofrane.fatnassi.service.interfaces.RegionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/distributions/regions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RegionController {

	@Autowired
    private RegionService regionService;

    //  Créer une région
    @PostMapping
    public ResponseEntity<Object> createRegion(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    //  Récupérer une région par son id
    @GetMapping("/{id}")
    public Optional<Region> getRegionById(@PathVariable Long id) {
        return regionService.getRegionById(id);
    }

    //  Mettre à jour une région
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegion(@PathVariable Long id, @RequestBody Region region) {
        return regionService.updateRegion(id, region);
    }

    //  Supprimer une région
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {
        return regionService.deleteRegion(id);
    }

    //  Liste de toutes les régions
    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

}
