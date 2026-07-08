# LOC MNS — Back-end (Spring Boot)

Application de gestion d'un parc de matériel empruntable (projet fil rouge CDA, examen TP-01281).
Front Angular dans un dépôt séparé (`frontend-LOCMNS-CDA-26`).

## Stack
- Java 25, Spring Boot 4.0.5, PostgreSQL 18
- Spring Security + JWT (jjwt 0.12.6), Spring Data JPA, Lombok, springdoc-openapi
- Build : `./mvnw.cmd test` / `./mvnw.cmd spring-boot:run` (JAVA_HOME = C:\Java\jdk-25.0.2+10)

## Conventions (IMPORTANT)
- **Aucun commentaire dans le code** (ni `//` ni Javadoc). Les explications vont dans le chat.
- **Aucune trace IA** dans les commits (pas de Co-Authored-By), messages en français, style Cyril.
- **Git Flow** : `feature/*` → `develop` → `main` via Pull Requests.
- Mode tuteur : Cyril code la logique métier, l'assistant guide. Code écrit par l'assistant uniquement sur blocage explicite, infra ou design.

## Architecture
- Couches : `controller` → `service` (règles métier) → `dao` (JpaRepository) → `model` (entités JPA).
- Sérialisation par `@JsonView` (vues dans `view/`) pour éviter la récursion bidirectionnelle.
  Toujours annoter un endpoint qui renvoie une entité avec la vue adéquate (sinon JSON invalide → "Http failure during parsing" côté front).
- Config via `.env` (importé par `spring.config.import`). En conteneur, surcharger `DB_HOST=postgres` par variable d'environnement (priorité sur le `.env`).

## Modèle (aligné sur le MCD)
Personne, Profil, FamilleMateriel, Materiel, Composant/TypeComposant, Documentation/TypeDocument,
Emplacement, Etat, Emprunt, Evenement.
- Un `Profil` donne accès à des `FamilleMateriel` (un profil ne peut pas tout emprunter, ex : casques VR).
- `Materiel.etat` → `Etat`. `Etat` porte `libelleEtat` + **`empruntable` (boolean)**.
  ⚠️ À reporter sur le MCD/MPD du dossier : l'attribut `empruntable` y est manquant.
- `Emprunt` : statutDemande (EN_ATTENTE, VALIDEE, REFUSEE, RETOURNE, ANNULEE), personne (emprunteur),
  traitePar (gestionnaire qui valide/refuse), recuPar (gestionnaire du retour).
- `Evenement` rattaché à un `Emprunt` (signalement : panne, retour anticipé, prolongation).

## Règles métier (EmpruntService)
- Une demande d'emprunt est refusée si :
  - dates incohérentes (`dateRetourPrevue <= dateDebut`),
  - matériel dont l'`Etat` n'est pas `empruntable` (la règle vit dans la donnée, jamais en dur sur un libellé),
  - profil non autorisé pour la famille du matériel,
  - **chevauchement** avec un emprunt EN_ATTENTE/VALIDEE/EN_COURS sur la période
    (`findChevauchements` : `dateDebut <= :fin AND dateRetourPrevue >= :debut`).
- Une réservation EN_ATTENTE bloque déjà la période (gère le cas "deux demandes concurrentes").
- `enregistrerRetour` met à jour `Emprunt` (RETOURNE) ET l'`Etat` du matériel.
- `annuler` : seulement par l'emprunteur, sur EN_ATTENTE/VALIDEE, avant le début.

## État d'avancement
- Fait : auth/RBAC, CRUD de toutes les entités, workflow d'emprunt complet, chevauchement,
  état bloquant, annulation, 10 tests unitaires (`src/test/java/.../unit` + mocks manuels `.../mock`, sans Mockito).
- À faire / en cours : signalement d'événement côté front, (option) proposition d'un exemplaire alternatif.
- Perspectives d'évolution (non codées, à présenter au jury) : planning visuel des réservations,
  système d'alertes (retards, nouvelles demandes), export CSV/XML des comptes.
- Déploiement (CP10/CP11) : Dockerfile multi-stage + compose, CI GitHub (modèle MyRecipe), VM du centre
  (192.168.23.18, accessible seulement sur le LAN). Voir REPRISE-DEPLOIEMENT.md à la racine du projet.

## Tests
Convention du formateur : dossiers `unit/` et `mock/`, **mocks manuels** (implémentent JpaRepository,
pas de Mockito), instanciation directe du service, schéma Arrange-Act-Assert.
