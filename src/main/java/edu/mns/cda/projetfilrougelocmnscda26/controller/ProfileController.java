package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.service.ProfileService;
import edu.mns.cda.projetfilrougelocmnscda26.view.ProfileView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import edu.mns.cda.projetfilrougelocmnscda26.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    protected final ProfileService profileService;

    @GetMapping("/liste")
    @JsonView(ProfileView.class)
    public List<Profile> getAll() {
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> get(@PathVariable int id) {

        Optional<Profile> optionalProfile = profileService.getById(id);

        if (optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(ProfileView.class)
    public ResponseEntity<Profile> create(
            @RequestBody
            @Valid
            Profile profileToInsert) {

        Profile profileCreate = profileService.create(profileToInsert);

        return new ResponseEntity<>(profileCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Profile profileToUpdate) {

        Optional<Profile> optionalProfile = profileService.update(id, profileToUpdate);

        if (optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean profileSupprime = profileService.supprimer(id);

        if (!profileSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
