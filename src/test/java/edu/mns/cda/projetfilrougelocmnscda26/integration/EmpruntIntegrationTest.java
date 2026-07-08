package edu.mns.cda.projetfilrougelocmnscda26.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
class EmpruntIntegrationTest {

    private final WebApplicationContext context;

    private final ObjectMapper mapper = JsonMapper.builder().build();
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    private Date date(int annee, int mois, int jour) {
        return new GregorianCalendar(annee, mois, jour).getTime();
    }

    @Test
    public void listerLesEmpruntsSansEtreConnecte_retourneCode403() throws Exception {
        mvc.perform(get("/emprunt/liste"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("j.dupont@mns.fr")
    public void demanderUnMaterielDejaReserveSurLaPeriode_retourneCode400() throws Exception {
        Materiel materiel = new Materiel();
        materiel.setId(1);

        Emprunt demande = new Emprunt();
        demande.setMateriel(materiel);
        demande.setDateDebut(date(2026, Calendar.SEPTEMBER, 5));
        demande.setDateRetourPrevue(date(2026, Calendar.SEPTEMBER, 15));

        String json = mapper.writeValueAsString(demande);

        mvc.perform(post("/emprunt/demande")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
