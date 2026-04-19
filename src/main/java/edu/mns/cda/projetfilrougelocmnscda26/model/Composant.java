package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.ComposantView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Composant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ComposantView.class)
    protected Integer id;

    @Column(columnDefinition = "TEXTE")
    @NotBlank
    @JsonView(ComposantView.class)
    protected String caracteristique;

    @ManyToMany(mappedBy = "composants")
    protected List<Materiel> materiels;

    @ManyToOne
    @JsonView(ComposantView.class)
    protected TypeComposant typeComposant;
}
