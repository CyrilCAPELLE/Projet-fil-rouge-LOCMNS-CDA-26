package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.MaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
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

    protected final MaterielDao materielDao;

    @GetMapping("/liste")
    @JsonView(MaterielView.class)
    public List<Materiel> getAll() {
        return materielDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiel> get(@PathVariable int id) {

        Optional<Materiel> optionalMateriel = materielDao.findById(id);

        if (optionalMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalMateriel.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> create(
            @RequestBody
            @Valid
            Materiel materielToInsert) {

        materielToInsert.setId(null);
        materielDao.save(materielToInsert);

        return new ResponseEntity<>(materielToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Materiel materielToUpdate) {

        Optional<Materiel> optionalMateriel = materielDao.findById(id);

        if (optionalMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        materielToUpdate.setId(id);

        materielDao.save(materielToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Materiel> optionalMateriel = materielDao.findById(id);

        if (optionalMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        materielDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
