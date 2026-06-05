package edu.mns.cda.projetfilrougelocmnscda26.controller;

import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import edu.mns.cda.projetfilrougelocmnscda26.security.PersonneDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Personne personne) {
        try {
            PersonneDetails personneDetails = (PersonneDetails) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            personne.getEmail(),
                            personne.getMotDePasse()))
                    .getPrincipal();

            String roles = personneDetails.getPersonne().getProfiles().stream()
                    .map(profile -> profile.getLibelleProfile())
                    .collect(Collectors.joining(","));

            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .subject(personne.getEmail())
                    .claim("roles", roles)
                    .signWith(SignatureAlgorithm.ES384, jwtSecret)
                    .compact();

            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}