package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EmpruntDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpruntService {

    private final EmpruntDao empruntDao;

    public List<Emprunt> getAll() {
        return empruntDao.findAll();
    }

    public Optional<Emprunt> getById(int id) {
        return empruntDao.findById(id);
    }

    public Emprunt create(Emprunt emprunt) {
        emprunt.setId(null);

        return empruntDao.save(emprunt);
    }

    public Optional<Emprunt> update(int id, Emprunt emprunt) {
        Optional<Emprunt> empruntUpdate = empruntDao.findById(id);

        if (empruntUpdate.isEmpty()) {
            return Optional.empty();
        }

        emprunt.setId(id);
        return Optional.of(empruntDao.save(emprunt));
    }

    public boolean supprimer(int id) {
        if (empruntDao.findById(id).isEmpty()) {
            return false;
        }
        empruntDao.deleteById(id);
        return true;
    }

}
