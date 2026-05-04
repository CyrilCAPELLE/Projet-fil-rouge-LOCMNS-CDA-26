package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.TypeDocumentDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeDocumentService {

    private final TypeDocumentDao typeDocumentDao;

    public List<TypeDocument> getAll() {
        return typeDocumentDao.findAll();
    }

    public Optional<TypeDocument> getById(int id) {
        return typeDocumentDao.findById(id);
    }

    public TypeDocument create(TypeDocument typeDocument) {
        typeDocument.setId(null);

        return typeDocumentDao.save(typeDocument);
    }

    public Optional<TypeDocument> update(int id, TypeDocument typeDocument) {
        Optional<TypeDocument> typeDocumentUpdate = typeDocumentDao.findById(id);

        if (typeDocumentUpdate.isEmpty()) {
            return Optional.empty();
        }

        typeDocument.setId(id);
        return Optional.of(typeDocumentDao.save(typeDocument));
    }

    public boolean supprimer(int id) {
        if (typeDocumentDao.findById(id).isEmpty()) {
            return false;
        }
        typeDocumentDao.deleteById(id);
        return true;
    }

}
