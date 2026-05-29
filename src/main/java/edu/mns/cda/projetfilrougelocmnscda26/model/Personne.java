package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.PersonneView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personne {

    public interface OnCreate {};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PersonneView.class)
    protected Integer id;

    @Column
    @NotBlank(groups = {OnCreate.class}, message = "Le nom ne peut pas être vide")
    @JsonView(PersonneView.class)
    protected String nom;

    @Column
    @NotBlank(groups = {OnCreate.class}, message = "Le prénom ne peut pas être vide")
    @JsonView(PersonneView.class)
    protected String prenom;

    @Column(nullable = false, unique = true)
    @NotBlank(groups = {OnCreate.class}, message = "L'email ne peut pas être vide")
    @Email(groups = {OnCreate.class}, message = "L'email est mal formé")
    @JsonView(PersonneView.class)
    protected String email;

    @Column(nullable = false)
    @NotNull
    @JsonView(PersonneView.class)
    protected Boolean actif;

    @Column(nullable = false, name = "mot_de_passe")
    @NotBlank(groups = {OnCreate.class}, message = "Le mot de passe ne peut pas être vide")
    protected String motDePasse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profile_personne",
            joinColumns = @JoinColumn(name = "personne_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    @JsonView(PersonneView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Profile> profiles = new ArrayList<>();




}
