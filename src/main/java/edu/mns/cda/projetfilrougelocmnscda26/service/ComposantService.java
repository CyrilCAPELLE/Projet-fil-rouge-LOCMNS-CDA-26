package edu.mns.cda.projetfilrougelocmnscda26.service;
import edu.mns.cda.projetfilrougelocmnscda26.dao.ComposantDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Composant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComposantService {

    private final ComposantDao composantDao;

    public List<Composant> getAll() {
        return composantDao.findAll();
    }

    public Optional<Composant> getById(int id) {
        return composantDao.findById(id);
    }

    public Composant create(Composant composant) {
        composant.setId(null);

        return composantDao.save(composant);
    }

    public Optional<Composant> update(int id, Composant composant) {
        Optional<Composant> composantUpdate = composantDao.findById(id);

        if (composantUpdate.isEmpty()) {
            return Optional.empty();
        }

        composant.setId(id);
        return Optional.of(composantDao.save(composant));
    }

    public boolean supprimer(int id) {
        if (composantDao.findById(id).isEmpty()) {
            return false;
        }
        composantDao.deleteById(id);
        return true;
    }

}
