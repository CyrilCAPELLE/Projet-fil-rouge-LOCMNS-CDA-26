package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.service.MaterielService;
import edu.mns.cda.projetfilrougelocmnscda26.view.MaterielView;
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
@RequestMapping("/materiel")
public class MaterielController {

    protected final MaterielService materielService;

    @GetMapping("/liste")
    @JsonView(MaterielView.class)
    public List<Materiel> getAll() {
        return materielService.getAll();
    }

    @GetMapping("/{id}")
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> get(@PathVariable int id) {

        Optional<Materiel> optionalMateriel = materielService.getById(id);

        if (optionalMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalMateriel.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(MaterielView.class)
    @IsAdmin
    public ResponseEntity<Materiel> create(
            @RequestBody
            @Valid
            Materiel materielToInsert) {

        Materiel materielCreate = materielService.create(materielToInsert);

        return new ResponseEntity<>(materielCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Materiel materielToUpdate) {

        Optional<Materiel> optionalMateriel = materielService.update(id, materielToUpdate);

        if (optionalMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/etat")
    @JsonView(MaterielView.class)
    @IsAdmin
    public Materiel changerEtat(@PathVariable int id, @RequestParam int nouvelEtatId) {
        return materielService.changerEtat(id, nouvelEtatId);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean materielSupprime = materielService.supprimer(id);

        if (!materielSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
