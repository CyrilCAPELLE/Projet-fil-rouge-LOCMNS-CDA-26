package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeComposant;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.service.TypeComposantService;
import edu.mns.cda.projetfilrougelocmnscda26.view.TypeComposantView;
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
@RequestMapping("/type-composant")
public class TypeComposantController {

    protected final TypeComposantService typeComposantService;

    @GetMapping("/liste")
    @JsonView(TypeComposantView.class)
    public List<TypeComposant> getAll() {
        return typeComposantService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeComposant> get(@PathVariable int id) {

        Optional<TypeComposant> optionalTypeComposant = typeComposantService.getById(id);

        if (optionalTypeComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTypeComposant.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(TypeComposantView.class)
    @IsAdmin
    public ResponseEntity<TypeComposant> create(
            @RequestBody
            @Valid
            TypeComposant typeComposantToInsert) {

        TypeComposant typeComposantCreate = typeComposantService.create(typeComposantToInsert);

        return new ResponseEntity<>(typeComposantCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            TypeComposant typeComposantToUpdate) {

        Optional<TypeComposant> optionalTypeComposant = typeComposantService.update(id, typeComposantToUpdate);

        if (optionalTypeComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean typeComposantSupprime = typeComposantService.supprimer(id);

        if (!typeComposantSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
