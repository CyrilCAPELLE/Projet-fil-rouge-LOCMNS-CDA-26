package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EtatView;
import edu.mns.cda.projetfilrougelocmnscda26.view.MaterielView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EtatView.class)
    protected Integer id;

    @Column
    @JsonView({EtatView.class, MaterielView.class})
    protected String libelleEtat;

    @Column(nullable = false)
    @JsonView({EtatView.class, MaterielView.class})
    protected boolean empruntable;

}
