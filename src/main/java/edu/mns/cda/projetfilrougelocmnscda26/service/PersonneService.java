package edu.mns.cda.projetfilrougelocmnscda26.service;

import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonneService {

    private final PersonneDao personneDao;
    private final PasswordEncoder passwordEncoder;

    public List<Personne> getAll() {
        return personneDao.findAll();
    }

    public List<Personne> listerAdministrateurs() {
        return personneDao.retourneListeSelonProfile("ADMIN");
    }

    public Optional<Personne> getById(int id) {
        return personneDao.findById(id);
    }

    public Personne create(Personne personne) {
        personne.setId(null);
        personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));

        return personneDao.save(personne);
    }

    public Optional<Personne> update(int id, Personne personne) {
        Optional<Personne> personneUpdate = personneDao.findById(id);

        if (personneUpdate.isEmpty()) {
            return Optional.empty();
        }

        personne.setId(id);
        return Optional.of(personneDao.save(personne));
    }

    public boolean supprimer(int id) {
        if (personneDao.findById(id).isEmpty()) {
            return false;
        }
        personneDao.deleteById(id);
        return true;
    }

    public Optional<Personne> anonymiser(int id) {
        Optional<Personne> personneAAnonymiser = personneDao.findById(id);

        if (personneAAnonymiser.isEmpty()) {
            return Optional.empty();
        }

        Personne personne = personneAAnonymiser.get();
        personne.setNom("Anonyme");
        personne.setPrenom("Anonyme");
        personne.setEmail("anonyme_" + id + "@anonyme.local");
        personne.setActif(false);

        return Optional.of(personneDao.save(personne));
    }
 }
