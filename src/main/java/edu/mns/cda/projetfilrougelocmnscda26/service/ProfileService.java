package edu.mns.cda.projetfilrougelocmnscda26.service;
import edu.mns.cda.projetfilrougelocmnscda26.dao.ProfileDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileDao profileDao;

    public List<Profile> getAll() {
        return profileDao.findAll();
    }

    public Optional<Profile> getById(int id) {
        return profileDao.findById(id);
    }

    public Profile create(Profile profile) {
        profile.setId(null);

        return profileDao.save(profile);
    }

    public Optional<Profile> update(int id, Profile profile) {
        Optional<Profile> profileUpdate = profileDao.findById(id);

        if (profileUpdate.isEmpty()) {
            return Optional.empty();
        }

        profile.setId(id);
        return Optional.of(profileDao.save(profile));
    }

    public boolean supprimer(int id) {
        if (profileDao.findById(id).isEmpty()) {
            return false;
        }
        profileDao.deleteById(id);
        return true;
    }

}
