package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
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
public class FamilleMateriel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(FamilleMaterielView.class)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    @JsonView(FamilleMaterielView.class)
    protected String libelleFamilleMateriel;

    @ManyToMany(mappedBy = "familleMateriels")
    protected List<Profile> profile;
}
