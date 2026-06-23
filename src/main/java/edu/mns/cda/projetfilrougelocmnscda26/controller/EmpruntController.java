package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.security.PersonneDetails;
import edu.mns.cda.projetfilrougelocmnscda26.service.EmpruntService;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @IsAdmin
    public List<Emprunt> getAll() {
        return empruntService.getAll();
    }

    @GetMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Emprunt> get(@PathVariable int id) {

        Optional<Emprunt> optionalEmprunt = empruntService.getById(id);

        if (optionalEmprunt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEmprunt.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EmpruntView.class)
    @IsAdmin
    public ResponseEntity<Emprunt> create(
            @RequestBody
            @Valid
            Emprunt empruntToInsert) {

        Emprunt empruntCreate =  empruntService.create(empruntToInsert);

        return new ResponseEntity<>(empruntCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @IsAdmin
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
    @IsAdmin
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
    public Emprunt creerDemande(@AuthenticationPrincipal PersonneDetails personneDetails,  @RequestBody Emprunt empruntInput) {
        return empruntService.creerDemande(
                personneDetails.getPersonne().getId(),
                empruntInput.getMateriel().getId(),
                empruntInput.getDateDebut(),
                empruntInput.getDateRetourPrevue()
        );
    }

    @PutMapping("/{id}/valider")
    @JsonView(EmpruntView.class)
    @IsAdmin
    public Emprunt valider(@AuthenticationPrincipal PersonneDetails personneDetails, @PathVariable int id) {
        return empruntService.valider(
                id,
                personneDetails.getPersonne().getId()
        );
    }

    @PutMapping("/{id}/refuser")
    @JsonView(EmpruntView.class)
    @IsAdmin
    public Emprunt refuser(@AuthenticationPrincipal PersonneDetails personneDetails, @PathVariable int id) {
        return empruntService.refuser(
                id,
                personneDetails.getPersonne().getId()
        );
    }

    @GetMapping("/personne/{personneId}")
    @JsonView(EmpruntView.class)
    public List<Emprunt> getMesDemandes(@AuthenticationPrincipal PersonneDetails personneDetails, @PathVariable int personneId) {
        int idConnecte = personneDetails.getPersonne().getId();

        boolean estAdmin = personneDetails.getAuthorities().stream().anyMatch(autorite -> autorite.getAuthority().equals("ROLE_ADMIN"));

        if (estAdmin || idConnecte == personneId) {
            return empruntService.getMesDemandes(personneId);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous ne pouvez consulter que vos propres demandes");
    }

    @PutMapping("/{id}/retour")
    @JsonView(EmpruntView.class)
    @IsAdmin
    public Emprunt enregistrerRetour(
            @PathVariable int id,
            @AuthenticationPrincipal PersonneDetails personneDetails,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRetour,
            @RequestParam int nouvelEtatId
    ) {
        return empruntService.enregistrerRetour(id, personneDetails.getPersonne().getId(), dateRetour, nouvelEtatId);
    }

    @PutMapping("/{id}/annuler")
    @JsonView(EmpruntView.class)
    public Emprunt annuler(@AuthenticationPrincipal PersonneDetails personneDetails, @PathVariable int id, @RequestParam int personneId) {
        int idConnecte = personneDetails.getPersonne().getId();

        boolean estAdmin = personneDetails.getAuthorities().stream().anyMatch(autorite -> autorite.getAuthority().equals("ROLE_ADMIN"));

        if (estAdmin || idConnecte == personneId) {
            return empruntService.annuler(id, personneId);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous ne pouvez pas annuler la demande");
    }
}
