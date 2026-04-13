package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EvenementView;
import edu.mns.cda.projetfilrougelocmnscda26.view.PersonneView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EvenementView.class)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EvenementView.class)
    protected String libelle;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(EvenementView.class)
    protected Date dateEvenement;

    @ManyToOne
    @JsonView(EvenementView.class)
    protected Emprunt emprunt;

}
