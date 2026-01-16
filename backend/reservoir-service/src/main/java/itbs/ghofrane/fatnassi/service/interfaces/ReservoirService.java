package itbs.ghofrane.fatnassi.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import itbs.ghofrane.fatnassi.entity.Reservoir;

public interface ReservoirService {
	ResponseEntity<Object> createReservoir(Reservoir reservoir);
    Optional<Reservoir> getReservoirById(Long id);
    ResponseEntity<Object> updateReservoir(Long id, Reservoir reservoir);
    ResponseEntity<Object> updateNiveau(Long id, double niveauActuel);
    ResponseEntity<Object> deleteReservoir(Long id);
    List<Reservoir> getAllReservoirs();
}
