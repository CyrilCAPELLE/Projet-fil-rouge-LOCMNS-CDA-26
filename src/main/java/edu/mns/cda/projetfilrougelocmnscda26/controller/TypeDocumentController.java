package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.TypeDocumentDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeDocument;
import edu.mns.cda.projetfilrougelocmnscda26.view.TypeDocumentView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/type-document")
public class TypeDocumentController {

    protected final TypeDocumentDao typeDocumentDao;

    @GetMapping("/liste")
    @JsonView(TypeDocumentView.class)
    public List<TypeDocument> getAll() {
        return typeDocumentDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDocument> get(@PathVariable int id) {

        Optional<TypeDocument> optionalTypeDocument = typeDocumentDao.findById(id);

        if (optionalTypeDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTypeDocument.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(TypeDocumentView.class)
    public ResponseEntity<TypeDocument> create(
            @RequestBody
            @Valid
            TypeDocument typeDocumentToInsert) {

        typeDocumentToInsert.setId(null);
        typeDocumentDao.save(typeDocumentToInsert);

        return new ResponseEntity<>(typeDocumentToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            TypeDocument typeDocumentToUpdate) {

        Optional<TypeDocument> optionalTypeDocument = typeDocumentDao.findById(id);

        if (optionalTypeDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        typeDocumentToUpdate.setId(id);

        typeDocumentDao.save(typeDocumentToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<TypeDocument> optionalTypeDocument = typeDocumentDao.findById(id);

        if (optionalTypeDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeDocumentDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
