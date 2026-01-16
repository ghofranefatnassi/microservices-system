package itbs.ghofrane.fatnassi.service.Implementations;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import itbs.ghofrane.fatnassi.entity.MesureNiveau;
import itbs.ghofrane.fatnassi.repository.MesureNiveauRepository;
import itbs.ghofrane.fatnassi.service.interfaces.MesureNiveauService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MesureNiveauServiceImpl implements MesureNiveauService{

	@Autowired
    private MesureNiveauRepository mesureNiveauRepository;

    @Override
    public ResponseEntity<Object> createMesure(MesureNiveau mesureNiveau) {

        // Si la date n’est pas fournie → maintenant
        if (mesureNiveau.getDateMesure() == null) {
            mesureNiveau.setDateMesure(LocalDateTime.now());
        }

        mesureNiveauRepository.save(mesureNiveau);
        log.info("Mesure de niveau ajoutée avec succès");

        return ResponseEntity.ok("Ajout avec succès");
    }

    @Override
    public Optional<MesureNiveau> getMesureById(Long id) {
        return mesureNiveauRepository.findById(id);
    }

    @Override
    public ResponseEntity<Object> updateMesure(Long id, MesureNiveau mesureNiveau) {

        MesureNiveau existante = mesureNiveauRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mesure non trouvée"));

        existante.setVolume(mesureNiveau.getVolume());
        existante.setDateMesure(mesureNiveau.getDateMesure());
        existante.setReservoir(mesureNiveau.getReservoir());

        mesureNiveauRepository.save(existante);
        log.info("Mesure de niveau mise à jour");

        return ResponseEntity.ok("Mise à jour réussie");
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteMesure(Long id) {

        mesureNiveauRepository.findById(id).ifPresentOrElse(
                mesure -> {
                    mesureNiveauRepository.deleteById(id);
                    log.info("Mesure supprimée avec succès");
                },
                () -> {
                    log.warn("Mesure avec id {} non trouvée", id);
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Mesure non trouvée");
                }
        );

        return ResponseEntity.ok("Suppression réussie");
    }

}
