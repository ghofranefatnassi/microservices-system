package itbs.ghofrane.fatnassi.controller;

import java.util.Optional;

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

import itbs.ghofrane.fatnassi.entity.MesureNiveau;
import itbs.ghofrane.fatnassi.service.interfaces.MesureNiveauService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservoirs/mesures") 
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class MesureNiveauController {

	private final MesureNiveauService mesureNiveauService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MesureNiveau mesure) {
        return mesureNiveauService.createMesure(mesure);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        Optional<MesureNiveau> mesure = mesureNiveauService.getMesureById(id);

        if (mesure.isPresent()) {
            return ResponseEntity.ok(mesure.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody MesureNiveau mesure) {
        return mesureNiveauService.updateMesure(id, mesure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return mesureNiveauService.deleteMesure(id);
    }
}
