INSERT INTO profile(libelle_profile) VALUES
    ('ADMIN'),
    ('COLLABORATEUR'),
    ('STAGIAIRE'),
    ('INTERVENANT');

INSERT INTO type_document(libelle_type_document) VALUES
    ('notice'),
    ('vidéo');

INSERT INTO documentation(titre_document, description, type_document_id) VALUES
    ('ASUS Vivobook', 'Documentation du ASUS Vivobook', 1),
    ('Oculus Quest 2', 'Documentation du Oculus Quest 2', 2);