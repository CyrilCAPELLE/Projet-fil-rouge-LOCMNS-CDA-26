package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EvenementDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Evenement;
import edu.mns.cda.projetfilrougelocmnscda26.view.EvenementView;
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
@RequestMapping("/evenement")
public class EvenementController {

    protected final EvenementDao evenementDao;

    @GetMapping("/liste")
    @JsonView(EvenementView.class)
    public List<Evenement> getAll() {
        return evenementDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> get(@PathVariable int id) {

        Optional<Evenement> optionalEvenement = evenementDao.findById(id);

        if (optionalEvenement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEvenement.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EvenementView.class)
    public ResponseEntity<Evenement> create(
            @RequestBody
            @Valid
            Evenement evenementToInsert) {

        evenementToInsert.setId(null);
        evenementDao.save(evenementToInsert);

        return new ResponseEntity<>(evenementToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Evenement evenementToUpdate) {

        Optional<Evenement> optionalEvenement = evenementDao.findById(id);

        if (optionalEvenement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        evenementToUpdate.setId(id);

        evenementDao.save(evenementToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Evenement> optionalEvenement = evenementDao.findById(id);

        if (optionalEvenement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        evenementDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
