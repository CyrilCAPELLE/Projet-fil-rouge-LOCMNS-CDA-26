package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EmpruntDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
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
@RequestMapping("/emprunt")
public class EmpruntController {

    protected final EmpruntDao empruntDao;

    @GetMapping("/liste")
    @JsonView(EmpruntView.class)
    public List<Emprunt> getAll() {
        return empruntDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> get(@PathVariable int id) {

        Optional<Emprunt> optionalEmprunt = empruntDao.findById(id);

        if (optionalEmprunt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEmprunt.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EmpruntView.class)
    public ResponseEntity<Emprunt> create(
            @RequestBody
            @Valid
            Emprunt empruntToInsert) {

        empruntToInsert.setId(null);
        empruntDao.save(empruntToInsert);

        return new ResponseEntity<>(empruntToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Emprunt empruntToUpdate) {

        Optional<Emprunt> optionalEmprunt = empruntDao.findById(id);

        if (optionalEmprunt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        empruntToUpdate.setId(id);

        empruntDao.save(empruntToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Emprunt> optionalEmprunt = empruntDao.findById(id);

        if (optionalEmprunt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        empruntDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
