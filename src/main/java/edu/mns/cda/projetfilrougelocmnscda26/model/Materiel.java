package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.MaterielView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MaterielView.class)
    protected Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @JsonView(MaterielView.class)
    protected String numeroDeSerie;

    @Column(nullable = false)
    @NotNull
    @JsonView(MaterielView.class)
    protected Date dateAchat;

    @ManyToOne
    @JsonView(MaterielView.class)
    protected FamilleMateriel familleMateriel;

    @ManyToOne
    @JsonView(MaterielView.class)
    protected Emplacement emplacement;

    @ManyToOne
    @JsonView(MaterielView.class)
    protected Etat etat;

    @ManyToMany
    @JoinTable(
            name = "documentation_materiel",
            joinColumns = @JoinColumn(name = "materiel_id"),
            inverseJoinColumns = @JoinColumn(name = "documentation_id")
    )
    @JsonView(MaterielView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Documentation> documentations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "composant_materiel",
            joinColumns = @JoinColumn(name = "materiel_id"),
            inverseJoinColumns = @JoinColumn(name = "composant_id")
    )
    @JsonView(MaterielView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Composant> composants = new ArrayList<>();

}
