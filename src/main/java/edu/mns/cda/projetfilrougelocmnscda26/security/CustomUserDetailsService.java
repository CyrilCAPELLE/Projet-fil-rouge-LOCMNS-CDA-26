package edu.mns.cda.projetfilrougelocmnscda26.security;

import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonneDao personneDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Optional<Personne> optionalPersonne = personneDao.findByEmail(email);

         if (optionalPersonne.isEmpty()) {
             throw new UsernameNotFoundException(email);
         }

         Personne personne = optionalPersonne.get();

        return new PersonneDetails(optionalPersonne.get());
    }
}
