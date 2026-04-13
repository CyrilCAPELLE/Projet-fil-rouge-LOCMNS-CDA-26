package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
import edu.mns.cda.projetfilrougelocmnscda26.view.PersonneView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EmpruntView.class)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected Date dateDebut;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected Date dateRetourPrevue;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected Date dateRetourPrevu;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected Date dateRetourReelle;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected Date dateDemande;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EmpruntView.class)
    protected String statutDemande;

    @ManyToOne
    @JsonView(EmpruntView.class)
    protected Personne personne;

    @ManyToOne
    @JsonView(EmpruntView.class)
    protected Materiel materiel;
}
