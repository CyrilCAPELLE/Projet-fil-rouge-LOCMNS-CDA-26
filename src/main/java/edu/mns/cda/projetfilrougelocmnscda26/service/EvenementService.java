package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EmpruntDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EtatDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.EvenementDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.MaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.model.Etat;
import edu.mns.cda.projetfilrougelocmnscda26.model.Evenement;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EvenementService {

    private static final Set<String> TYPES_AUTORISES =
            Set.of("PANNE", "DYSFONCTIONNEMENT", "RETOUR_ANTICIPE", "PROLONGATION");

    private final EvenementDao evenementDao;
    private final EmpruntDao empruntDao;
    private final MaterielDao materielDao;
    private final EtatDao etatDao;
    private final EmpruntService empruntService;

    public List<Evenement> getAll() {
        return evenementDao.findAll();
    }

    public Optional<Evenement> getById(int id) {
        return evenementDao.findById(id);
    }

    public Evenement create(Evenement evenement) {
        evenement.setId(null);

        return evenementDao.save(evenement);
    }

    public Optional<Evenement> update(int id, Evenement evenement) {
        Optional<Evenement> evenementUpdate = evenementDao.findById(id);

        if (evenementUpdate.isEmpty()) {
            return Optional.empty();
        }

        evenement.setId(id);
        return Optional.of(evenementDao.save(evenement));
    }

    public boolean supprimer(int id) {
        if (evenementDao.findById(id).isEmpty()) {
            return false;
        }
        evenementDao.deleteById(id);
        return true;
    }

    public Evenement signaler(int empruntId, int personneId, String typeEvenement, String libelleEvenement) {

        if (typeEvenement == null || !TYPES_AUTORISES.contains(typeEvenement)) {
            throw new IllegalArgumentException("Type d'événement invalide : " + typeEvenement);
        }

        Emprunt emprunt = empruntDao.findById(empruntId)
                .orElseThrow(() -> new IllegalArgumentException("Emprunt introuvable : " + empruntId));

        if (!emprunt.getPersonne().getId().equals(personneId)) {
            throw new IllegalArgumentException("Vous ne pouvez signaler un événement que sur vos propres emprunts");
        }

        if (!"VALIDEE".equals(emprunt.getStatutDemande())) {
            throw new IllegalArgumentException(
                    "Un événement ne peut être signalé que sur un emprunt en cours (statut actuel : " + emprunt.getStatutDemande() + ")"
            );
        }

        Evenement evenement = new Evenement();
        evenement.setEmprunt(emprunt);
        evenement.setTypeEvenement(typeEvenement);
        evenement.setLibelleEvenement(libelleEvenement);

        return evenementDao.save(evenement);
    }

    private Evenement trouverNonTraite(int evenementId) {
        Evenement evenement = evenementDao.findById(evenementId)
                .orElseThrow(() -> new IllegalArgumentException("Événement introuvable : " + evenementId));

        if (evenement.isTraite()) {
            throw new IllegalArgumentException("Cet événement a déjà été traité");
        }
        return evenement;
    }

    public Evenement marquerTraite(int evenementId) {
        Evenement evenement = trouverNonTraite(evenementId);
        evenement.setTraite(true);
        return evenementDao.save(evenement);
    }

    public Evenement mettreEnMaintenance(int evenementId, int nouvelEtatId) {
        Evenement evenement = trouverNonTraite(evenementId);

        Etat nouvelEtat = etatDao.findById(nouvelEtatId)
                .orElseThrow(() -> new IllegalArgumentException("État introuvable : " + nouvelEtatId));

        Materiel materiel = evenement.getEmprunt().getMateriel();
        materiel.setEtat(nouvelEtat);
        materielDao.save(materiel);

        evenement.setTraite(true);
        return evenementDao.save(evenement);
    }

    public Evenement prolonger(int evenementId, Date nouvelleDateRetour) {
        Evenement evenement = trouverNonTraite(evenementId);

        Emprunt emprunt = evenement.getEmprunt();
        if (!nouvelleDateRetour.after(emprunt.getDateRetourPrevue())) {
            throw new IllegalArgumentException("La nouvelle date de retour doit être après la date de retour prévue actuelle");
        }

        emprunt.setDateRetourPrevue(nouvelleDateRetour);
        empruntDao.save(emprunt);

        evenement.setTraite(true);
        return evenementDao.save(evenement);
    }

    public Evenement traiterRetourAnticipe(int evenementId, int adminId, Date dateRetour, int nouvelEtatId) {
        Evenement evenement = trouverNonTraite(evenementId);

        empruntService.enregistrerRetour(evenement.getEmprunt().getId(), adminId, dateRetour, nouvelEtatId);

        evenement.setTraite(true);
        return evenementDao.save(evenement);
    }

}
