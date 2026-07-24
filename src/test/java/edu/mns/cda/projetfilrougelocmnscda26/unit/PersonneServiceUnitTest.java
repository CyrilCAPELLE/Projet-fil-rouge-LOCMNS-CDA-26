package edu.mns.cda.projetfilrougelocmnscda26.unit;

import edu.mns.cda.projetfilrougelocmnscda26.mock.MockPersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import edu.mns.cda.projetfilrougelocmnscda26.service.PersonneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PersonneServiceUnitTest {

    @Test
    public void anonymiser_personneExistante_effaceLesDonneesEtDesactive() {
        PersonneService personneService = new PersonneService(new MockPersonneDao(), null);

        Optional<Personne> resultat = personneService.anonymiser(1);

        Assertions.assertTrue(resultat.isPresent());
        Personne personne = resultat.get();
        Assertions.assertEquals("Anonyme", personne.getNom());
        Assertions.assertEquals("Anonyme", personne.getPrenom());
        Assertions.assertEquals("anonyme_1@anonyme.local", personne.getEmail());
        Assertions.assertFalse(personne.getActif());
    }

    @Test
    public void anonymiser_personneInexistante_retourneOptionalVide() {
        PersonneService personneService = new PersonneService(new MockPersonneDao(), null);

        Optional<Personne> resultat = personneService.anonymiser(999);

        Assertions.assertTrue(resultat.isEmpty());
    }
}