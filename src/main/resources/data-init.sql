INSERT INTO profile(libelle_profile) VALUES
                                         ('ADMIN'),
                                         ('COLLABORATEUR'),
                                         ('STAGIAIRE'),
                                         ('INTERVENANT');

INSERT INTO etat(libelle_etat) VALUES
                                   ('Neuf'),
                                   ('Bon état'),
                                   ('Usé'),
                                   ('En réparation'),
                                   ('Hors service');

INSERT INTO emplacement(libelle_emplacement) VALUES
                                                 ('Salle 101'),
                                                 ('Salle 202'),
                                                 ('Salle 303'),
                                                 ('Stock informatique'),
                                                 ('Accueil'),
                                                 ('Salle de réunion');

INSERT INTO famille_materiel(libelle_famille_materiel) VALUES
                                                           ('PC Portable'),
                                                           ('PC Fixe'),
                                                           ('Écran'),
                                                           ('Vidéoprojecteur'),
                                                           ('Casque VR'),
                                                           ('Tablette'),
                                                           ('Imprimante'),
                                                           ('Accessoire');

INSERT INTO type_composant(libelle_type_composant) VALUES
                                                       ('Processeur'),
                                                       ('Carte graphique'),
                                                       ('Barrette RAM'),
                                                       ('Disque SSD'),
                                                       ('Disque HDD'),
                                                       ('Carte mère'),
                                                       ('Alimentation');

INSERT INTO type_document(libelle_type_document) VALUES
                                                     ('Notice'),
                                                     ('Vidéo'),
                                                     ('Fiche technique'),
                                                     ('Guide utilisateur');

INSERT INTO personne(nom, prenom, email, actif, mot_de_passe) VALUES
                                                                  ('Dupont', 'Jean', 'j.dupont@mns.fr', true, 'password123'),
                                                                  ('Martin', 'Sophie', 's.martin@mns.fr', true, 'password123'),
                                                                  ('Leroy', 'Marc', 'm.leroy@mns.fr', true, 'password123'),
                                                                  ('Bernard', 'Claire', 'c.bernard@mns.fr', true, 'password123'),
                                                                  ('Petit', 'Thomas', 't.petit@mns.fr', true, 'password123'),
                                                                  ('Moreau', 'Julie', 'j.moreau@mns.fr', false, 'password123');

INSERT INTO profile_personne(personne_id, profile_id) VALUES
                                                          (1, 1),
                                                          (2, 2),
                                                          (3, 3),
                                                          (4, 3),
                                                          (5, 4),
                                                          (6, 2);

-- Quel profil a le droit d'emprunter quelle famille de matériel
INSERT INTO famille_profile(profile_id, famille_materiel_id) VALUES
                                                                 -- ADMIN : toutes les familles
                                                                 (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
                                                                 -- COLLABORATEUR : bureautique + vidéoprojecteur + tablette
                                                                 (2, 1), (2, 2), (2, 3), (2, 4), (2, 6), (2, 7), (2, 8),
                                                                 -- STAGIAIRE : minimum (portable + tablette + accessoire)
                                                                 (3, 1), (3, 6), (3, 8),
                                                                 -- INTERVENANT : matériel pédagogique
                                                                 (4, 1), (4, 3), (4, 4), (4, 5), (4, 6), (4, 8);

INSERT INTO materiel(nom_materiel, numero_de_serie, date_achat, famille_materiel_id, emplacement_id, etat_id) VALUES
                                                                                                                  ('Dell Latitude 5540', 'DL5540-001', '2024-09-15', 1, 1, 1),
                                                                                                                  ('Dell Latitude 5540', 'DL5540-002', '2024-09-15', 1, 2, 2),
                                                                                                                  ('HP EliteBook 840 G10', 'HPE840-001', '2024-06-20', 1, 1, 1),
                                                                                                                  ('Lenovo ThinkPad T14', 'LTP14-001', '2023-11-10', 1, 4, 3),
                                                                                                                  ('Dell OptiPlex 7010', 'DO7010-001', '2024-03-05', 2, 1, 1),
                                                                                                                  ('HP ProDesk 400 G9', 'HPP400-001', '2024-03-05', 2, 2, 2),
                                                                                                                  ('Dell UltraSharp U2723QE', 'DU2723-001', '2024-01-12', 3, 1, 1),
                                                                                                                  ('Dell UltraSharp U2723QE', 'DU2723-002', '2024-01-12', 3, 2, 1),
                                                                                                                  ('HP E24 G5', 'HPE24-001', '2024-04-18', 3, 3, 2),
                                                                                                                  ('BenQ MH560', 'BMH560-001', '2023-09-01', 4, 6, 2),
                                                                                                                  ('Epson EB-W52', 'EBW52-001', '2023-09-01', 4, 5, 3),
                                                                                                                  ('Meta Quest 3', 'MQ3-001', '2024-12-01', 5, 4, 1),
                                                                                                                  ('Meta Quest 3', 'MQ3-002', '2024-12-01', 5, 4, 1),
                                                                                                                  ('iPad Pro 11 M4', 'IPP11-001', '2024-10-22', 6, 1, 1),
                                                                                                                  ('Samsung Galaxy Tab S9', 'SGT9-001', '2024-08-30', 6, 3, 2),
                                                                                                                  ('HP LaserJet Pro M404', 'HPLJ-001', '2023-06-15', 7, 4, 2);

INSERT INTO composant(caracteristique, type_composant_id) VALUES
                                                              ('Intel Core i7-1365U', 1),
                                                              ('Intel Core i5-1345U', 1),
                                                              ('AMD Ryzen 5 7530U', 1),
                                                              ('NVIDIA GeForce RTX 3050', 2),
                                                              ('Intel Iris Xe', 2),
                                                              ('16 Go DDR5 4800 MHz', 3),
                                                              ('8 Go DDR4 3200 MHz', 3),
                                                              ('512 Go NVMe Samsung', 4),
                                                              ('256 Go NVMe Western Digital', 4),
                                                              ('1 To HDD Seagate', 5);

INSERT INTO documentation(titre_document, description, date_ajout, type_document_id) VALUES
                                                                                         ('Notice Dell Latitude 5540', 'Manuel utilisateur complet du Dell Latitude 5540', '2024-09-15', 1),
                                                                                         ('Guide HP EliteBook 840', 'Guide de démarrage rapide HP EliteBook', '2024-06-20', 4),
                                                                                         ('Vidéo Meta Quest 3', 'Tutoriel de prise en main du Meta Quest 3', '2024-12-01', 2),
                                                                                         ('Fiche technique Epson EB-W52', 'Caractéristiques du vidéoprojecteur Epson', '2023-09-01', 3);

INSERT INTO composant_materiel(materiel_id, composant_id) VALUES
                                                              (1, 1), (1, 5), (1, 6), (1, 8),
                                                              (2, 1), (2, 5), (2, 6), (2, 8),
                                                              (3, 2), (3, 5), (3, 7), (3, 9),
                                                              (4, 3), (4, 4), (4, 6), (4, 8),
                                                              (5, 2), (5, 5), (5, 6), (5, 8),
                                                              (6, 2), (6, 5), (6, 7), (6, 9);

INSERT INTO documentation_materiel(materiel_id, documentation_id) VALUES
                                                                      (1, 1), (2, 1),
                                                                      (3, 2),
                                                                      (12, 3), (13, 3),
                                                                      (11, 4);

-- 2 demandes d'emprunt en attente, pour tester valider/refuser sans repasser par POST /demande
INSERT INTO emprunt(date_debut, date_retour_prevue, date_demande, statut_demande, personne_id, materiel_id) VALUES
                                                                                                                ('2026-09-01', '2026-09-10', NOW(), 'EN_ATTENTE', 3, 1),  -- Leroy (STAGIAIRE) demande PC Portable
                                                                                                                ('2026-10-15', '2026-10-20', NOW(), 'EN_ATTENTE', 2, 12); -- Martin (COLLAB) demande Meta Quest 3