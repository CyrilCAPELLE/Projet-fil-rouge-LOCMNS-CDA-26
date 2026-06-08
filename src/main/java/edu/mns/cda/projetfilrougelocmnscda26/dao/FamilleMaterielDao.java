package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.FamilleMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FamilleMaterielDao extends JpaRepository<FamilleMateriel, Integer> {

    @Query("SELECT DISTINCT f FROM Personne p JOIN p.profiles pr JOIN pr.familleMateriels f WHERE p.id = :personneId")
    List<FamilleMateriel> findAccessiblesByPersonne(int personneId);

}
