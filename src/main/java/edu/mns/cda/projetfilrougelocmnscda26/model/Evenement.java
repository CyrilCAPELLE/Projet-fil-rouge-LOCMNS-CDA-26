package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EvenementView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
