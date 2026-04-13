package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationDao extends JpaRepository<Documentation, Integer> {
}
