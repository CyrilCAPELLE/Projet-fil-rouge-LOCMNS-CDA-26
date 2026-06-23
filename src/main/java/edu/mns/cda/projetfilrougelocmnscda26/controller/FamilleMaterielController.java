package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.FamilleMateriel;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.security.PersonneDetails;
import edu.mns.cda.projetfilrougelocmnscda26.service.FamilleMaterielService;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/famille-materiel")
public class FamilleMaterielController {

    protected final FamilleMaterielService familleMaterielService;

    @GetMapping("/liste")
    @JsonView(FamilleMaterielView.class)
    public List<FamilleMateriel> getAll() {
        return familleMaterielService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilleMateriel> get(@PathVariable int id) {

        Optional<FamilleMateriel> optionalFamilleMateriel = familleMaterielService.getById(id);

        if (optionalFamilleMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalFamilleMateriel.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(FamilleMaterielView.class)
    @IsAdmin
    public ResponseEntity<FamilleMateriel> create(
            @RequestBody
            @Valid
            FamilleMateriel familleMaterielToInsert) {

        FamilleMateriel familleMaterielCreate = familleMaterielService.create(familleMaterielToInsert);

        return new ResponseEntity<>(familleMaterielCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            FamilleMateriel familleMaterielToUpdate) {

        Optional<FamilleMateriel> optionalFamilleMateriel = familleMaterielService.update(id, familleMaterielToUpdate);

        if (optionalFamilleMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean familleMaterielSupprimee = familleMaterielService.supprimer(id);

        if (!familleMaterielSupprimee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accessibles")
    @JsonView(FamilleMaterielView.class)
    public List<FamilleMateriel> getAccessibles(@AuthenticationPrincipal PersonneDetails personneDetails) {
        return familleMaterielService.getAccessibles(personneDetails.getPersonne().getId());
    }
}
