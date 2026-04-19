package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.TypeComposantDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.TypeComposant;
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

    protected final TypeComposantDao typeComposantDao;

    @GetMapping("/liste")
    @JsonView(TypeComposantView.class)
    public List<TypeComposant> getAll() {
        return typeComposantDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeComposant> get(@PathVariable int id) {

        Optional<TypeComposant> optionalTypeComposant = typeComposantDao.findById(id);

        if (optionalTypeComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTypeComposant.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(TypeComposantView.class)
    public ResponseEntity<TypeComposant> create(
            @RequestBody
            @Valid
            TypeComposant typeComposantToInsert) {

        typeComposantToInsert.setId(null);
        typeComposantDao.save(typeComposantToInsert);

        return new ResponseEntity<>(typeComposantToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            TypeComposant typeComposantToUpdate) {

        Optional<TypeComposant> optionalTypeComposant = typeComposantDao.findById(id);

        if (optionalTypeComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        typeComposantToUpdate.setId(id);

        typeComposantDao.save(typeComposantToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<TypeComposant> optionalTypeComposant = typeComposantDao.findById(id);

        if (optionalTypeComposant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        typeComposantDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
