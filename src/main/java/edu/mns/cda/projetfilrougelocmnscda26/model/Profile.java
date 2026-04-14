package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
import edu.mns.cda.projetfilrougelocmnscda26.view.ProfileView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {

    public interface OnCreate {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ProfileView.class)
    protected Integer id;

    @Column(nullable = false)
    @JsonView(ProfileView.class)
    protected String libelle;

    @ManyToMany
    @JoinTable(
            name = "acceder",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "famille_id")
    )
    @JsonView(FamilleMaterielView.class)
    protected List<FamilleMateriel> familleMateriels = new ArrayList<>();
}
