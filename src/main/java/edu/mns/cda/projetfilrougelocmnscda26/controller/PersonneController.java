package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import edu.mns.cda.projetfilrougelocmnscda26.view.PersonneView;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/personne")
@Tag(name = "Personne", description = "API pour manipuler les utilisateurs.")
public class PersonneController {

    protected final PersonneDao personneDao;

    @GetMapping("/liste")
    @JsonView(PersonneView.class)
    public List<Personne> getAll() {
        return personneDao.findAll();
    }

    @GetMapping("/liste-admin")
    @JsonView(PersonneView.class)
    public List<Personne> getAllAdmin() { return personneDao.retourneListeSelonProfile("ADMIN");}

    @GetMapping("/{id}")
    public ResponseEntity<Personne> get(@PathVariable int id) {

        Optional<Personne> optionalPersonne = personneDao.findById(id);

        if (optionalPersonne.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalPersonne.get(), HttpStatus.OK);
    }

    @PostMapping
    @JsonView(PersonneView.class)
    public ResponseEntity<Personne> create(
            @RequestBody
            @Valid
            Personne personneToInsert) {

        personneToInsert.setId(null);
        personneDao.save(personneToInsert);

        return new ResponseEntity<>(personneToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Personne personneToUpdate) {

        Optional<Personne> optionalPersonne = personneDao.findById(id);

        if (optionalPersonne.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        personneToUpdate.setId(id);

        personneDao.save(personneToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Personne> optionalPersonne = personneDao.findById(id);

        if (optionalPersonne.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personneDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
