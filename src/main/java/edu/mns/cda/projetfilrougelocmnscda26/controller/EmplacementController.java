package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EmplacementDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emplacement;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmplacementView;
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
@RequestMapping("/emplacement")
public class EmplacementController {

    protected final EmplacementDao emplacementDao;

    @GetMapping("/liste")
    @JsonView(EmplacementView.class)
    public List<Emplacement> getAll() {
        return emplacementDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emplacement> get(@PathVariable int id) {

        Optional<Emplacement> optionalEmplacement = emplacementDao.findById(id);

        if (optionalEmplacement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEmplacement.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EmplacementView.class)
    public ResponseEntity<Emplacement> create(
            @RequestBody
            @Valid
            Emplacement emplacementToInsert) {

        emplacementToInsert.setId(null);
        emplacementDao.save(emplacementToInsert);

        return new ResponseEntity<>(emplacementToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Emplacement emplacementToUpdate) {

        Optional<Emplacement> optionalEmplacement = emplacementDao.findById(id);

        if (optionalEmplacement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        emplacementToUpdate.setId(id);

        emplacementDao.save(emplacementToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Emplacement> optionalEmplacement = emplacementDao.findById(id);

        if (optionalEmplacement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        emplacementDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
