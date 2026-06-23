package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Composant;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.service.ComposantService;
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

    protected final ComposantService composantService;

    @GetMapping("/liste")
    @JsonView(ComposantView.class)
    public List<Composant> getAll() {
        return composantService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Composant> get(@PathVariable int id) {

        Optional<Composant> optionalComposant = composantService.getById(id);

        if (optionalComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalComposant.get(), HttpStatus.OK);

    }

    @PostMapping
    @IsAdmin
    @JsonView(ComposantView.class)
    public ResponseEntity<Composant> create(
            @RequestBody
            @Valid
            Composant composantToInsert) {

        Composant composantCreate = composantService.create(composantToInsert);

        return new ResponseEntity<>(composantCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Composant composantToUpdate) {

        Optional<Composant> optionalComposant = composantService.update(id, composantToUpdate);

        if (optionalComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean composantSupprime = composantService.supprimer(id);

        if (!composantSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
