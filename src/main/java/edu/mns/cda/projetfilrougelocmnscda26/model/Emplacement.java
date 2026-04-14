package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.EmplacementView;
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
public class Emplacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EmplacementView.class)
    protected Integer id;

    @Column
    @NotBlank
    @JsonView(EmplacementView.class)
    protected String libelleEmplacement;

}
