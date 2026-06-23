package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeDocument;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.service.TypeDocumentService;
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

    protected final TypeDocumentService typeDocumentService;

    @GetMapping("/liste")
    @JsonView(TypeDocumentView.class)
    public List<TypeDocument> getAll() {
        return typeDocumentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDocument> get(@PathVariable int id) {

        Optional<TypeDocument> optionalTypeDocument = typeDocumentService.getById(id);

        if (optionalTypeDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTypeDocument.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(TypeDocumentView.class)
    @IsAdmin
    public ResponseEntity<TypeDocument> create(
            @RequestBody
            @Valid
            TypeDocument typeDocumentToInsert) {

        TypeDocument typeDocumentCreate = typeDocumentService.create(typeDocumentToInsert);

        return new ResponseEntity<>(typeDocumentCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            TypeDocument typeDocumentToUpdate) {

        Optional<TypeDocument> optionalTypeDocument = typeDocumentService.update(id, typeDocumentToUpdate);

        if (optionalTypeDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean typeDocumentSupprime = typeDocumentService.supprimer(id);

        if (!typeDocumentSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
