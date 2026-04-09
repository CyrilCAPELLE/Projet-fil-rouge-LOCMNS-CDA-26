package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.ProfileDao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import edu.mns.cda.projetfilrougelocmnscda26.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    protected final ProfileDao profileDao;

    @GetMapping("/liste")
    @JsonView(ProfileDao.class)
    public List<Profile> getAll() {
        return profileDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> get(@PathVariable int id) {

        Optional<Profile> optionalProfile = profileDao.findById(id);

        if (optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(ProfileDao.class)
    public ResponseEntity<Profile> create(
            @RequestBody
            @Valid
            Profile profileToInsert) {

        profileToInsert.setId(null);
        profileDao.save(profileToInsert);

        return new ResponseEntity<>(profileToInsert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Profile profileToUpdate) {

        Optional<Profile> optionalProfile = profileDao.findById(id);

        if (optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        profileToUpdate.setId(id);

        profileDao.save(profileToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Optional<Profile> optionalProfile = profileDao.findById(id);

        if (optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        profileDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
