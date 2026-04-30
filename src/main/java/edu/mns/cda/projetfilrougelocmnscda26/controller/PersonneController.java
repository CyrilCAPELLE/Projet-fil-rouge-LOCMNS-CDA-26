package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import edu.mns.cda.projetfilrougelocmnscda26.service.PersonneService;
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

    protected final PersonneService personneService;

    @GetMapping("/liste")
    @JsonView(PersonneView.class)
    public List<Personne> getAll() {
        return personneService.getAll();
    }

    @GetMapping("/liste-admin")
    @JsonView(PersonneView.class)
    public List<Personne> getAllAdmin() { return personneService.listerAdministrateurs();}

    @GetMapping("/{id}")
    public ResponseEntity<Personne> get(@PathVariable int id) {

        Optional<Personne> optionalPersonne = personneService.getById(id);

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

        Personne personneCreate = personneService.create(personneToInsert);

        return new ResponseEntity<>(personneCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Personne personneToUpdate) {

        Optional<Personne> optionalPersonne = personneService.update(id, personneToUpdate);

        if (optionalPersonne.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean personneSupprimee = personneService.supprimer(id);

        if (!personneSupprimee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
