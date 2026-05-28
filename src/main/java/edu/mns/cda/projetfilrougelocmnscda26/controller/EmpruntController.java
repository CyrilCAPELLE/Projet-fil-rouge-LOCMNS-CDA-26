package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.service.EmpruntService;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/emprunt")
public class EmpruntController {

    protected final EmpruntService empruntService;

    @GetMapping("/liste")
    @JsonView(EmpruntView.class)
    public List<Emprunt> getAll() {
        return empruntService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> get(@PathVariable int id) {

        Optional<Emprunt> optionalEmprunt = empruntService.getById(id);

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

        Emprunt empruntCreate =  empruntService.create(empruntToInsert);

        return new ResponseEntity<>(empruntCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Emprunt empruntToUpdate) {

        Optional<Emprunt> optionalEmprunt = empruntService.update(id, empruntToUpdate);

        if (optionalEmprunt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean empruntSupprime = empruntService.supprimer(id);

        if (!empruntSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/demande")
    @JsonView(EmpruntView.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Emprunt creerDemande(@RequestBody Emprunt empruntInput) {
        return empruntService.creerDemande(
                empruntInput.getPersonne().getId(),
                empruntInput.getMateriel().getId(),
                empruntInput.getDateDebut(),
                empruntInput.getDateRetourPrevue()
        );
    }

    @PutMapping("/{id}/valider")
    @JsonView(EmpruntView.class)
    public Emprunt valider(@PathVariable int id, @RequestParam int adminId) {
        return empruntService.valider(id, adminId);
    }

    @PutMapping("/{id}/refuser")
    @JsonView(EmpruntView.class)
    public Emprunt refuser(@PathVariable int id, @RequestParam int adminId) {
        return empruntService.refuser(id, adminId);
    }

    @GetMapping("/personne/{personneId}")
    @JsonView(EmpruntView.class)
    public List<Emprunt> getMesDemandes(@PathVariable int personneId) {
        return empruntService.getMesDemandes(personneId);
    }

    @PutMapping("/{id}/retour")
    @JsonView(EmpruntView.class)
    public Emprunt enregistrerRetour(
            @PathVariable int id,
            @RequestParam int adminId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRetour,
            @RequestParam int nouvelEtatId
    ) {
        return empruntService.enregistrerRetour(id, adminId, dateRetour, nouvelEtatId);
    }

    @PutMapping("/{id}/annuler")
    @JsonView(EmpruntView.class)
    public Emprunt annuler(@PathVariable int id, @RequestParam int personneId) {
        return empruntService.annuler(id, personneId);
    }
}
