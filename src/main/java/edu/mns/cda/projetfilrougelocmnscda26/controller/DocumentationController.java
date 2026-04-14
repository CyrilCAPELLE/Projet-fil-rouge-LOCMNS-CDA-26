package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.DocumentationDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Documentation;
import edu.mns.cda.projetfilrougelocmnscda26.view.DocumentationView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documentation")
public class DocumentationController {

    protected final DocumentationDao documentationDao;

    @GetMapping("/liste")
    @JsonView(DocumentationView.class)
    public List<Documentation> getAll() {
        return documentationDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documentation> get(@PathVariable int id) {

        Optional<Documentation> optionalDocumentation = documentationDao.findById(id);

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
            Documentation DocumentationToInsert) {

        DocumentationToInsert.setId(null);
        documentationDao.save(DocumentationToInsert);

        return new ResponseEntity<>(DocumentationToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Documentation documentationToUpdate) {

        Optional<Documentation> optionalDocumentation = documentationDao.findById(id);

        if (optionalDocumentation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        documentationToUpdate.setId(id);

        documentationDao.save(documentationToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Documentation> optionalDocumentation = documentationDao.findById(id);

        if (optionalDocumentation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        documentationDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
