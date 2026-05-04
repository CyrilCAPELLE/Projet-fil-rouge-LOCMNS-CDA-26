package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.FamilleMaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.FamilleMateriel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilleMaterielService {

    private final FamilleMaterielDao familleMaterielDao;

    public List<FamilleMateriel> getAll() {
        return familleMaterielDao.findAll();
    }

    public Optional<FamilleMateriel> getById(int id) {
        return familleMaterielDao.findById(id);
    }

    public FamilleMateriel create(FamilleMateriel familleMateriel) {
        familleMateriel.setId(null);

        return familleMaterielDao.save(familleMateriel);
    }

    public Optional<FamilleMateriel> update(int id, FamilleMateriel familleMateriel) {
        Optional<FamilleMateriel> familleEmpruntUpdate = familleMaterielDao.findById(id);

        if (familleEmpruntUpdate.isEmpty()) {
            return Optional.empty();
        }

        familleMateriel.setId(id);
        return Optional.of(familleMaterielDao.save(familleMateriel));
    }

    public boolean supprimer(int id) {
        if (familleMaterielDao.findById(id).isEmpty()) {
            return false;
        }
        familleMaterielDao.deleteById(id);
        return true;
    }

}
