package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EtatDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.MaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Etat;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterielService {

    private final MaterielDao materielDao;
    private final EtatDao etatDao;

    public List<Materiel> getAll() {
        return materielDao.findAll();
    }

    public Optional<Materiel> getById(int id) {
        return materielDao.findById(id);
    }

    public Materiel create(Materiel materiel) {
        materiel.setId(null);

        return materielDao.save(materiel);
    }

    public Optional<Materiel> update(int id, Materiel materiel) {
        Optional<Materiel> materielUpdate = materielDao.findById(id);

        if (materielUpdate.isEmpty()) {
            return Optional.empty();
        }

        materiel.setId(id);
        return Optional.of(materielDao.save(materiel));
    }

    public boolean supprimer(int id) {
        if (materielDao.findById(id).isEmpty()) {
            return false;
        }
        materielDao.deleteById(id);
        return true;
    }

    public Materiel changerEtat(int id, int nouvelEtatId) {
        Materiel materiel = materielDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matériel introuvable : " + id));

        Etat etat = etatDao.findById(nouvelEtatId)
                .orElseThrow(() -> new IllegalArgumentException("État introuvable : " + nouvelEtatId));

        materiel.setEtat(etat);
        return materielDao.save(materiel);
    }

}
