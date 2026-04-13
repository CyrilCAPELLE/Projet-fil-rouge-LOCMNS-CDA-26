package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.dao.ProfileDao;
import jakarta.persistence.*;
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
public class Profile {

    public interface OnCreate {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    @JsonView(ProfileDao.class)
    protected String libelle;

    @ManyToMany
    @JoinTable(
            name = "acceder",
            joinColumns = @JoinColumn(name = "id_profile"),
            inverseJoinColumns = @JoinColumn(name = "id_famille")
    )
    private List<FamilleMateriel> familles;

}
