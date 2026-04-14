package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    @NotBlank()
    @JsonView(FamilleMaterielView.class)
    protected String libelle;
}
