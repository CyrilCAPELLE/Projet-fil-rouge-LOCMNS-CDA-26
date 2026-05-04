package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EtatDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Etat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtatService {

    private final EtatDao etatDao;

    public List<Etat> getAll() {
        return etatDao.findAll();
    }

    public Optional<Etat> getById(int id) {
        return etatDao.findById(id);
    }

    public Etat create(Etat etat) {
        etat.setId(null);

        return etatDao.save(etat);
    }

    public Optional<Etat> update(int id, Etat etat) {
        Optional<Etat> etatUpdate = etatDao.findById(id);

        if (etatUpdate.isEmpty()) {
            return Optional.empty();
        }

        etat.setId(id);
        return Optional.of(etatDao.save(etat));
    }

    public boolean supprimer(int id) {
        if (etatDao.findById(id).isEmpty()) {
            return false;
        }
        etatDao.deleteById(id);
        return true;
    }

}
