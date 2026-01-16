package itbs.ghofrane.fatnassi.controller;

import java.util.List;

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

import itbs.ghofrane.fatnassi.entity.Reservoir;
import itbs.ghofrane.fatnassi.service.interfaces.ReservoirService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservoirs") 
@CrossOrigin(origins = "http://localhost:4200")  
@RequiredArgsConstructor
public class ReservoirController {
	
	private final ReservoirService reservoirService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Reservoir reservoir) {
        return reservoirService.createReservoir(reservoir);
    }
    
    @GetMapping
    public ResponseEntity<List<Reservoir>> getAllReservoirs() {
        return ResponseEntity.ok(reservoirService.getAllReservoirs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        return reservoirService.getReservoirById(id)
                .<ResponseEntity<Object>>map(reservoir ->
                        ResponseEntity.ok((Object) reservoir)
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody Reservoir reservoir) {
        return reservoirService.updateReservoir(id, reservoir);
    }
    @PutMapping("/{id}/niveau/{niveau}")
    public ResponseEntity<Object> updateNiveau(
            @PathVariable Long id,
            @PathVariable double niveau) {
        return reservoirService.updateNiveau(id, niveau);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return reservoirService.deleteReservoir(id);
    }
}
