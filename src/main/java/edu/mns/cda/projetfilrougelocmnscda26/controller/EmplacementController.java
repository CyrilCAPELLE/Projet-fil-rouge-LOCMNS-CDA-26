package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emplacement;
import edu.mns.cda.projetfilrougelocmnscda26.service.EmplacementService;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmplacementView;
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
@RequestMapping("/emplacement")
public class EmplacementController {

    protected final EmplacementService emplacementService;

    @GetMapping("/liste")
    @JsonView(EmplacementView.class)
    public List<Emplacement> getAll() {
        return emplacementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emplacement> get(@PathVariable int id) {

        Optional<Emplacement> optionalEmplacement = emplacementService.getById(id);

        if (optionalEmplacement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEmplacement.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EmplacementView.class)
    public ResponseEntity<Emplacement> create(
            @RequestBody
            @Valid
            Emplacement emplacementToInsert) {

        Emplacement emplacementCreate = emplacementService.create(emplacementToInsert);

        return new ResponseEntity<>(emplacementCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Emplacement emplacementToUpdate) {

        Optional<Emplacement> optionalEmplacement = emplacementService.update(id, emplacementToUpdate);

        if (optionalEmplacement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean emplacementSupprime = emplacementService.supprimer(id);

        if (!emplacementSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
