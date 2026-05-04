package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EmplacementDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emplacement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmplacementService {

    private final EmplacementDao emplacementDao;

    public List<Emplacement> getAll() {
        return emplacementDao.findAll();
    }

    public Optional<Emplacement> getById(int id) {
        return emplacementDao.findById(id);
    }

    public Emplacement create(Emplacement emplacement) {
        emplacement.setId(null);

        return emplacementDao.save(emplacement);
    }

    public Optional<Emplacement> update(int id, Emplacement emplacement) {
        Optional<Emplacement> emplacementUpdate = emplacementDao.findById(id);

        if (emplacementUpdate.isEmpty()) {
            return Optional.empty();
        }

        emplacement.setId(id);
        return Optional.of(emplacementDao.save(emplacement));
    }

    public boolean supprimer(int id) {
        if (emplacementDao.findById(id).isEmpty()) {
            return false;
        }
        emplacementDao.deleteById(id);
        return true;
    }

}
