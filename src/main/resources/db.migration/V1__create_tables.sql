-- Script SQL pour Flyway (V1__create_tables.sql)

-- Création de la table Etablissement
CREATE TABLE etablissement (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Création de la table Classe
CREATE TABLE classe (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES etablissement(id) ON DELETE SET NULL
);

-- Création de la table Eleve
CREATE TABLE eleve (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    matricule INT NOT NULL,
    classe_id BIGINT,
    FOREIGN KEY (classe_id) REFERENCES classe(id) ON DELETE SET NULL
);

-- Création de la table Matiere
CREATE TABLE matiere (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    coefficient DOUBLE NOT NULL,
    classe_id BIGINT,
    FOREIGN KEY (classe_id) REFERENCES classe(id) ON DELETE SET NULL
);

-- Création de la table Note
CREATE TABLE note (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    note DOUBLE NOT NULL,
    appreciation VARCHAR(255),
    matiere_id BIGINT,
    eleve_id BIGINT,
    FOREIGN KEY (matiere_id) REFERENCES matiere(id) ON DELETE CASCADE,
    FOREIGN KEY (eleve_id) REFERENCES eleve(id) ON DELETE CASCADE
);
