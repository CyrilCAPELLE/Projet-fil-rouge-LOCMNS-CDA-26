package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.PersonneView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PersonneView.class)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(PersonneView.class)
    protected String nom;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(PersonneView.class)
    protected String prenom;

    @Column(nullable = false)
    @NotBlank()
    @Email
    @JsonView
    protected String email;

    @Column(nullable = false)
    @NotBlank()
    @JsonView(PersonneView.class)
    protected Boolean actif;

    @Column(nullable = false)
    @NotBlank()
    @JsonView
    protected String mot_de_passe;

    @ManyToMany
    @JsonView(PersonneView.class)
    protected Profile profile;



}
