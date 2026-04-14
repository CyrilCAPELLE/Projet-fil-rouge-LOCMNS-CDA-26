package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.FamilleMaterielView;
import edu.mns.cda.projetfilrougelocmnscda26.view.ProfileView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

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

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    @Length(min = 3, max = 20)
    @JsonView(ProfileView.class)
    protected String libelleProfile;

    @ManyToMany(mappedBy = "profiles")
    protected List<Personne> personnes;

    @ManyToMany
    @JoinTable(
            name = "famille_profile",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "familleMateriel_id")
    )
    @JsonView(FamilleMaterielView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<FamilleMateriel> familleMateriels = new ArrayList<>();
}
