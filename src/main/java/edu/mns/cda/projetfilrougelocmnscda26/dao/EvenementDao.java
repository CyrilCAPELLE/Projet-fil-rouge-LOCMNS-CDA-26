package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementDao extends JpaRepository<Evenement, Integer> {
}
