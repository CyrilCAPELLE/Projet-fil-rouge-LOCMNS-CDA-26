package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EtatDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Etat;
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

    protected final EtatDao etatDao;

    @GetMapping("/liste")
    @JsonView(EtatView.class)
    public List<Etat> getAll() {
        return etatDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etat> get(@PathVariable int id) {

        Optional<Etat> optionalEtat = etatDao.findById(id);

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

        etatToInsert.setId(null);
        etatDao.save(etatToInsert);

        return new ResponseEntity<>(etatToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Etat etatToUpdate) {

        Optional<Etat> optionalEtat = etatDao.findById(id);

        if (optionalEtat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        etatToUpdate.setId(id);

        etatDao.save(etatToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Etat> optionalEtat = etatDao.findById(id);

        if (optionalEtat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        etatDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
