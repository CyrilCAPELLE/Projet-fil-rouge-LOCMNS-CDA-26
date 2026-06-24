package edu.mns.cda.projetfilrougelocmnscda26.unit;

import edu.mns.cda.projetfilrougelocmnscda26.mock.MockMaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.mock.MockPersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.service.EmpruntService;
import edu.mns.cda.projetfilrougelocmnscda26.mock.MockEmpruntDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

public class EmpruntServiceUnitTest {

    @Test
    public void creerDemandeAvecDateRetourAvantDebut_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(null, null, null, null);

        Date debut = new Date();
        Date retour = new Date(debut.getTime() - 86_400_000L);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.creerDemande(1, 1, debut, retour);
        });
    }

    @Test
    public void creerDemandeAvecDatesEgales_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(null, null, null, null);

        Date date = new Date();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.creerDemande(1, 1, date, date);
        });
    }

    @Test
    public void validerSurUnEmpruntInexistant_shouldThrowException() {
        EmpruntService empruntService =new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.valider(999, 99);
        });
    }

    @Test
    public void refuserSurUnEmpruntInexistant_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.refuser(999, 99);
        });
    }

    @Test
    public void enregistrerRetourSurUnEmpruntInexistant() {
        EmpruntService empruntService = new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.enregistrerRetour(999, 99, new Date(), 1);
        });
    }

    @Test
    public void creerDemandeAvecPersonneIntrouvable() {
        EmpruntService empruntService = new EmpruntService(null, new MockPersonneDao(), null, null);

        Date debut = new Date();
        Date retour = new Date(debut.getTime() + 86_400_000L);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.creerDemande(999, 1, debut, retour);
        });
    }

    @Test
    public void creerDemandeAvecMaterielIntrouvable() {
        EmpruntService empruntService = new EmpruntService(null, new MockPersonneDao(), new MockMaterielDao(), null);

        Date debut = new Date();
        Date retour = new Date(debut.getTime() + 86_400_000L);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.creerDemande(1, 999, debut, retour);
        });
    }

    @Test
    public void validerEmpruntNonEnAttente_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.valider(1, 99);
        });
    }

    @Test
    public void refuserEmpruntNonEnAttente_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.refuser(1, 99);
        });
    }

    @Test
    public void enregistrerRetourEmpruntNonValide_shouldThrowException() {
        EmpruntService empruntService = new EmpruntService(new MockEmpruntDao(), null, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            empruntService.enregistrerRetour(2, 99, new Date(), 1);
        });
    }
}