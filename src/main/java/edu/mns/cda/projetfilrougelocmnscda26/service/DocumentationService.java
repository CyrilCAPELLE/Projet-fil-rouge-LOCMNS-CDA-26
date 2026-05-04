package edu.mns.cda.projetfilrougelocmnscda26.service;
import edu.mns.cda.projetfilrougelocmnscda26.dao.DocumentationDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Documentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentationService {

    private final DocumentationDao documentationDao;

    public List<Documentation> getAll() {
        return documentationDao.findAll();
    }

    public Optional<Documentation> getById(int id) {
        return documentationDao.findById(id);
    }

    public Documentation create(Documentation documentation) {
        documentation.setId(null);

        return documentationDao.save(documentation);
    }

    public Optional<Documentation> update(int id, Documentation documentation) {
        Optional<Documentation> documentationUpdate = documentationDao.findById(id);

        if (documentationUpdate.isEmpty()) {
            return Optional.empty();
        }

        documentation.setId(id);
        return Optional.of(documentationDao.save(documentation));
    }

    public boolean supprimer(int id) {
        if (documentationDao.findById(id).isEmpty()) {
            return false;
        }
        documentationDao.deleteById(id);
        return true;
    }

}
