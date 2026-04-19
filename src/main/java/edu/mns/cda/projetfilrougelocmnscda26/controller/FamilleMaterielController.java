package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.FamilleMaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.FamilleMateriel;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
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
@RequestMapping("/famille-materiel")
public class FamilleMaterielController {

    protected final FamilleMaterielDao familleMaterielDao;

    @GetMapping("/liste")
    @JsonView(FamilleMaterielView.class)
    public List<FamilleMateriel> getAll() {
        return familleMaterielDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilleMateriel> get(@PathVariable int id) {

        Optional<FamilleMateriel> optionalFamilleMateriel = familleMaterielDao.findById(id);

        if (optionalFamilleMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalFamilleMateriel.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(FamilleMaterielView.class)
    public ResponseEntity<FamilleMateriel> create(
            @RequestBody
            @Valid
            FamilleMateriel familleMaterielToInsert) {

        familleMaterielToInsert.setId(null);
        familleMaterielDao.save(familleMaterielToInsert);

        return new ResponseEntity<>(familleMaterielToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            FamilleMateriel familleMaterielToUpdate) {

        Optional<FamilleMateriel> optionalFamilleMateriel = familleMaterielDao.findById(id);

        if (optionalFamilleMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        familleMaterielToUpdate.setId(id);

        familleMaterielDao.save(familleMaterielToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<FamilleMateriel> optionalFamilleMateriel = familleMaterielDao.findById(id);

        if (optionalFamilleMateriel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        familleMaterielDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
