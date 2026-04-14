package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmpruntView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EmpruntView.class)
    protected Integer id;

    @Column(nullable = false)
    @NotNull
    @JsonView(EmpruntView.class)
    protected Date dateDebut;

    @Column(nullable = false)
    @NotNull
    @JsonView(EmpruntView.class)
    protected Date dateRetourPrevue;

    @Column
    @JsonView(EmpruntView.class)
    protected Date dateRetourReelle;

    @Column(nullable = false)
    @NotNull
    @JsonView(EmpruntView.class)
    protected Date dateDemande;

    @Column(nullable = false)
    @NotBlank
    @JsonView(EmpruntView.class)
    protected String statutDemande;

    @ManyToOne
    @JsonView(EmpruntView.class)
    protected Personne personne;

    @ManyToOne
    @JsonView(EmpruntView.class)
    protected Personne traitePar;

    @ManyToOne
    @JsonView(EmpruntView.class)
    protected Materiel materiel;
}
