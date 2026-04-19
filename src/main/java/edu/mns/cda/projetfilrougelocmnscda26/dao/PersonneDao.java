package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import edu.mns.cda.projetfilrougelocmnscda26.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneDao extends JpaRepository<Personne, Integer> {


    @Query("SELECT p FROM Personne p JOIN p.profiles pr WHERE pr.libelleProfile = :nomProfile")
    List<Personne> retourneListeSelonProfile(@Param("nomProfile") String profile);
}
