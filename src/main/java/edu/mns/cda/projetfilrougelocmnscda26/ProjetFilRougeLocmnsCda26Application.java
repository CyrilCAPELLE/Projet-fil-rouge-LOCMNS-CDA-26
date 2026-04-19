package edu.mns.cda.projetfilrougelocmnscda26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetFilRougeLocmnsCda26Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFilRougeLocmnsCda26Application.class, args);
    }

}
