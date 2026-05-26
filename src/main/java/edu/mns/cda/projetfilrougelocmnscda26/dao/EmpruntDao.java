package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {

    @Query("""
    SELECT e FROM Emprunt e 
    WHERE e.materiel.id = :materielId 
    AND e.statutDemande IN ('EN_ATTENTE', 'VALIDEE', 'EN_COURS')
    AND e.dateDebut <= :dateRetourPrevue 
    AND e.dateRetourPrevue >= :dateDebut
""")
    List<Emprunt> findChevauchements(
            @Param("materielId") int materielId,
            @Param("dateDebut") Date dateDebut,
            @Param("dateRetourPrevue") Date dateRetourPrevue
    );
}
