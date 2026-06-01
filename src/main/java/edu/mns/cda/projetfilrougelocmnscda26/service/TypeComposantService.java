package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.TypeComposantDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeComposant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeComposantService {

    private final TypeComposantDao typeComposantDao;

    public List<TypeComposant> getAll() {
        return typeComposantDao.findAll();
    }

    public Optional<TypeComposant> getById(int id) {
        return typeComposantDao.findById(id);
    }

    public TypeComposant create(TypeComposant typeComposant) {
        typeComposant.setId(null);

        return typeComposantDao.save(typeComposant);
    }

    public Optional<TypeComposant> update(int id, TypeComposant typeComposant) {
        Optional<TypeComposant> typeComposantUpdate = typeComposantDao.findById(id);

        if (typeComposantUpdate.isEmpty()) {
            return Optional.empty();
        }

        typeComposant.setId(id);
        return Optional.of(typeComposantDao.save(typeComposant));
    }

    public boolean supprimer(int id) {
        if (typeComposantDao.findById(id).isEmpty()) {
            return false;
        }
        typeComposantDao.deleteById(id);
        return true;
    }

}
