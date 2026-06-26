package edu.mns.cda.projetfilrougelocmnscda26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.model.Evenement;
import edu.mns.cda.projetfilrougelocmnscda26.security.IsAdmin;
import edu.mns.cda.projetfilrougelocmnscda26.security.PersonneDetails;
import edu.mns.cda.projetfilrougelocmnscda26.service.EvenementService;
import edu.mns.cda.projetfilrougelocmnscda26.view.EvenementView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/evenement")
public class EvenementController {

    protected final EvenementService evenementService;

    @GetMapping("/liste")
    @JsonView(EvenementView.class)
    @IsAdmin
    public List<Evenement> getAll() {
        return evenementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> get(@PathVariable int id) {

        Optional<Evenement> optionalEvenement = evenementService.getById(id);

        if (optionalEvenement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEvenement.get(), HttpStatus.OK);

    }

    @PostMapping
    @JsonView(EvenementView.class)
    @IsAdmin
    public ResponseEntity<Evenement> create(
            @RequestBody
            @Valid
            Evenement evenementToInsert) {

        Evenement evenementCreate = evenementService.create(evenementToInsert);

        return new ResponseEntity<>(evenementCreate, HttpStatus.CREATED);
    }

    @PostMapping("/signaler")
    @JsonView(EvenementView.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Evenement signaler(
            @AuthenticationPrincipal PersonneDetails personneDetails,
            @RequestParam int empruntId,
            @RequestParam String typeEvenement,
            @RequestParam String libelleEvenement) {
        return evenementService.signaler(
                empruntId,
                personneDetails.getPersonne().getId(),
                typeEvenement,
                libelleEvenement
        );
    }

    @PutMapping("/{id}/traiter")
    @JsonView(EvenementView.class)
    @IsAdmin
    public Evenement marquerTraite(@PathVariable int id) {
        return evenementService.marquerTraite(id);
    }

    @PutMapping("/{id}/maintenance")
    @JsonView(EvenementView.class)
    @IsAdmin
    public Evenement mettreEnMaintenance(@PathVariable int id, @RequestParam int nouvelEtatId) {
        return evenementService.mettreEnMaintenance(id, nouvelEtatId);
    }

    @PutMapping("/{id}/prolonger")
    @JsonView(EvenementView.class)
    @IsAdmin
    public Evenement prolonger(
            @PathVariable int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date nouvelleDateRetour) {
        return evenementService.prolonger(id, nouvelleDateRetour);
    }

    @PutMapping("/{id}/retour-anticipe")
    @JsonView(EvenementView.class)
    @IsAdmin
    public Evenement traiterRetourAnticipe(
            @AuthenticationPrincipal PersonneDetails personneDetails,
            @PathVariable int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRetour,
            @RequestParam int nouvelEtatId) {
        return evenementService.traiterRetourAnticipe(id, personneDetails.getPersonne().getId(), dateRetour, nouvelEtatId);
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody
            @Valid
            Evenement evenementToUpdate) {

        Optional<Evenement> optionalEvenement = evenementService.update(id, evenementToUpdate);

        if (optionalEvenement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {

        boolean evenementSupprime = evenementService.supprimer(id);

        if (!evenementSupprime) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
