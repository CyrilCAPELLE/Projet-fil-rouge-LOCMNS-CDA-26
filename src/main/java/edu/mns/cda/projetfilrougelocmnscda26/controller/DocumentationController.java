package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Documentation;
import edu.mns.cda.projetfilrougelocmnscda26.service.DocumentationService;
import edu.mns.cda.projetfilrougelocmnscda26.view.DocumentationView;
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
@RequestMapping("/documentation")
public class DocumentationController {

    protected final DocumentationService documentationService;

    @GetMapping("/liste")
    @JsonView(DocumentationView.class)
    public List<Documentation> getAll() {
        return documentationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documentation> get(@PathVariable int id) {

        Optional<Documentation> optionalDocumentation = documentationService.getById(id);

        if (optionalDocumentation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalDocumentation.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(DocumentationView.class)
    public ResponseEntity<Documentation> create(
            @RequestBody
            @Valid
            Documentation documentationToInsert) {

        Documentation documentationCreate = documentationService.create(documentationToInsert);

        return new ResponseEntity<>(documentationCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Documentation documentationToUpdate) {

        Optional<Documentation> optionalDocumentation = documentationService.update(id, documentationToUpdate);

        if (optionalDocumentation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean documentationSupprimee = documentationService.supprimer(id);

        if (!documentationSupprimee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
