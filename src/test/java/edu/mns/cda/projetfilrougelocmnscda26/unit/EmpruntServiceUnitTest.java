package edu.mns.cda.projetfilrougelocmnscda26.unit;

import edu.mns.cda.projetfilrougelocmnscda26.service.EmpruntService;
import edu.mns.cda.projetfilrougelocmnscda26.mock.MockEmpruntDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

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
}