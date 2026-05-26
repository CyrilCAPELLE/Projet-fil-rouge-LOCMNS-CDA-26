package edu.mns.cda.projetfilrougelocmnscda26.security;

import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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

        return User.builder()
                .username(personne.getEmail())
                .password(personne.getMotDePasse())
                .roles("USER")
                .build();
    }
}
