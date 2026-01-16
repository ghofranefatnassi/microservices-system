package itbs.ghofrane.fatnassi.service.interfaces;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import itbs.ghofrane.fatnassi.entity.MesureNiveau;

public interface MesureNiveauService {
	ResponseEntity<Object> createMesure(MesureNiveau mesureNiveau);
    Optional<MesureNiveau> getMesureById(Long id);
    ResponseEntity<Object> updateMesure(Long id, MesureNiveau mesureNiveau);
    ResponseEntity<Object> deleteMesure(Long id);
}
