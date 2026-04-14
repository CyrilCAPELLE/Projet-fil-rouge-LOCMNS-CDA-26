package edu.mns.cda.projetfilrougelocmnscda26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.cda.projetfilrougelocmnscda26.view.DocumentationView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documentation {

    public interface OnCreate {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(DocumentationView.class)
    protected Integer id;

    @Column(nullable = false)
    @JsonView(DocumentationView.class)
    protected String titreDocument;

    @Column
    @JsonView(DocumentationView.class)
    protected Date dateAjout;

    @Column(nullable = false)
    @JsonView(DocumentationView.class)
    protected String description;

    @ManyToMany(mappedBy = "documentations")
    protected List<Materiel> materiel;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(DocumentationView.class)
    protected TypeDocument typeDocument;
}
