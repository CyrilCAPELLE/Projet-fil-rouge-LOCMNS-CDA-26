package controller;

import com.fasterxml.jackson.annotation.JsonView;
import dao.ProfileDao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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



}
