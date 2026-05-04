package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EvenementDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Evenement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvenementService {

    private final EvenementDao evenementDao;

    public List<Evenement> getAll() {
        return evenementDao.findAll();
    }

    public Optional<Evenement> getById(int id) {
        return evenementDao.findById(id);
    }

    public Evenement create(Evenement evenement) {
        evenement.setId(null);

        return evenementDao.save(evenement);
    }

    public Optional<Evenement> update(int id, Evenement evenement) {
        Optional<Evenement> evenementUpdate = evenementDao.findById(id);

        if (evenementUpdate.isEmpty()) {
            return Optional.empty();
        }

        evenement.setId(id);
        return Optional.of(evenementDao.save(evenement));
    }

    public boolean supprimer(int id) {
        if (evenementDao.findById(id).isEmpty()) {
            return false;
        }
        evenementDao.deleteById(id);
        return true;
    }

}
