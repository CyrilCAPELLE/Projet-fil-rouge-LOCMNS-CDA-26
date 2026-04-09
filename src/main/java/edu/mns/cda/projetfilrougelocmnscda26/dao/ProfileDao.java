package edu.mns.cda.projetfilrougelocmnscda26.dao;

import edu.mns.cda.projetfilrougelocmnscda26.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Integer> {
}
