package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {
}
