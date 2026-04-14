package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.TypeComposantView;
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
public class TypeComposant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TypeComposantView.class)
    protected Integer id;

    @Column
    @JsonView(TypeComposantView.class)
    protected String libelleTypeComposant;
}
