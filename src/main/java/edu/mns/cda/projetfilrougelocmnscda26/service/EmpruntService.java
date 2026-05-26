package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.EmpruntDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.MaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.model.FamilleMateriel;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpruntService {

    private final EmpruntDao empruntDao;
    private final PersonneDao personneDao;
    private final MaterielDao materielDao;

    public List<Emprunt> getAll() {
        return empruntDao.findAll();
    }

    public Optional<Emprunt> getById(int id) {
        return empruntDao.findById(id);
    }

    public Emprunt create(Emprunt emprunt) {
        emprunt.setId(null);

        return empruntDao.save(emprunt);
    }

    public Optional<Emprunt> update(int id, Emprunt emprunt) {
        Optional<Emprunt> empruntUpdate = empruntDao.findById(id);

        if (empruntUpdate.isEmpty()) {
            return Optional.empty();
        }

        emprunt.setId(id);
        return Optional.of(empruntDao.save(emprunt));
    }

    public boolean supprimer(int id) {
        if (empruntDao.findById(id).isEmpty()) {
            return false;
        }
        empruntDao.deleteById(id);
        return true;
    }

    public Emprunt creerDemande(int personneId, int materielId, Date dateDebut, Date dateRetourPrevue) {

        if (dateRetourPrevue.before(dateDebut) || dateRetourPrevue.equals(dateDebut)) {
            throw new IllegalArgumentException("La date de retour doit être après la date de début");
        }

        Personne personne = personneDao.findById(personneId)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable : " + personneId));

        Materiel materiel = materielDao.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel introuvable : " + materielId));

        if (materiel.getEtat() == null) {
            throw new IllegalArgumentException("Le matériel n'a pas d'état défini, donc non empruntable");
        }

        String libelleEtat = materiel.getEtat().getLibelleEtat().toLowerCase();
        if (!libelleEtat.equals("disponible") && !libelleEtat.equals("neuf")) {
            throw new IllegalArgumentException("Le matériel n'est pas empruntable (état actuel : " + libelleEtat + ")");
        }

        FamilleMateriel familleDuMateriel = materiel.getFamilleMateriel();

        if (familleDuMateriel == null) {
            throw new IllegalArgumentException("Le matériel n'a pas de famille définie");
        }

        boolean profilAutorise = personne.getProfiles().stream()
                .flatMap(profile -> profile.getFamilleMateriels().stream())
                .anyMatch(famille -> famille.getId().equals(familleDuMateriel.getId()));

        if (!profilAutorise) {
            throw new IllegalArgumentException("Votre profil ne vous autorise pas à emprunter ce type de matériel");
        }

        List<Emprunt> chevauchements = empruntDao.findChevauchements(materielId, dateDebut, dateRetourPrevue);
        if (!chevauchements.isEmpty()) {
            throw new IllegalArgumentException("Ce matériel est déjà réservé sur tout ou partie de la période demandée");
        }

        Emprunt nouvelEmprunt = new Emprunt();
        nouvelEmprunt.setPersonne(personne);
        nouvelEmprunt.setMateriel(materiel);
        nouvelEmprunt.setDateDebut(dateDebut);
        nouvelEmprunt.setDateRetourPrevue(dateRetourPrevue);
        nouvelEmprunt.setStatutDemande("EN_ATTENTE");

        return empruntDao.save(nouvelEmprunt);

    }


}
