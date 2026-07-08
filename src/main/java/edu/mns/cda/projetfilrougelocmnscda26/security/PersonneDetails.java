package edu.mns.cda.projetfilrougelocmnscda26.security;

import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class PersonneDetails implements UserDetails {

    protected Personne personne;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return personne.getProfiles().stream()
                .map(profile -> new SimpleGrantedAuthority("ROLE_" + profile.getLibelleProfile()))
                .toList();
    }

    @Override
    public String getPassword() {
        return personne.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return personne.getEmail();
    }

    @Override
    public boolean isEnabled() { return Boolean.TRUE.equals(personne.getActif()); }

}