package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.ComposantDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Composant;
import edu.mns.cda.projetfilrougelocmnscda26.view.ComposantView;
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
@RequestMapping("/composant")
public class ComposantController {

    protected final ComposantDao composantDao;

    @GetMapping("/liste")
    @JsonView(ComposantView.class)
    public List<Composant> getAll() {
        return composantDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Composant> get(@PathVariable int id) {

        Optional<Composant> optionalComposant = composantDao.findById(id);

        if (optionalComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalComposant.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(ComposantView.class)
    public ResponseEntity<Composant> create(
            @RequestBody
            @Valid
            Composant composantToInsert) {

        composantToInsert.setId(null);
        composantDao.save(composantToInsert);

        return new ResponseEntity<>(composantToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Composant composantToUpdate) {

        Optional<Composant> optionalComposant = composantDao.findById(id);

        if (optionalComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        composantToUpdate.setId(id);

        composantDao.save(composantToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Composant> optionalComposant = composantDao.findById(id);

        if (optionalComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        composantDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
