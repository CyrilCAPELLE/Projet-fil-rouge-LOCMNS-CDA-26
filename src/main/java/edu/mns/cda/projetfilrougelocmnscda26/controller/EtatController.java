package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Etat;
import edu.mns.cda.projetfilrougelocmnscda26.service.EtatService;
import edu.mns.cda.projetfilrougelocmnscda26.view.EtatView;
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
@RequestMapping("/etat")
public class EtatController {

    protected final EtatService etatService;

    @GetMapping("/liste")
    @JsonView(EtatView.class)
    public List<Etat> getAll() {
        return etatService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etat> get(@PathVariable int id) {

        Optional<Etat> optionalEtat = etatService.getById(id);

        if (optionalEtat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEtat.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EtatView.class)
    public ResponseEntity<Etat> create(
            @RequestBody
            @Valid
            Etat etatToInsert) {

        Etat etatCreate = etatService.create(etatToInsert);

        return new ResponseEntity<>(etatCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Etat etatToUpdate) {

        Optional<Etat> optionalEtat = etatService.update(id, etatToUpdate);

        if (optionalEtat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean etatSupprime = etatService.supprimer(id);

        if (!etatSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
