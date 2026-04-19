INSERT INTO profile(libelle_profile) VALUES
    ('ADMIN'),
    ('COLLABORATEUR'),
    ('STAGIAIRE'),
    ('INTERVENANT');

INSERT INTO type_document(libelle_type_document) VALUES
    ('notice'),
    ('vidéo');

INSERT INTO documentation(titre_document, description, date_ajout, type_document_id) VALUES
    ('ASUS Vivobook', 'Documentation du ASUS Vivobook', '2026-04-17', 1),
    ('Oculus Quest 2', 'Documentation du Oculus Quest 2', '2026-04-17', 2);