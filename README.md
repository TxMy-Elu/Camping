# Application de Gestion de Camping

## Table des Matières

1. [Introduction](#introduction)
2. [Fonctionnalités](#fonctionnalités)
3. [Installation](#installation)
4. [Utilisation](#utilisation)
5. [Structure des Fichiers](#structure-des-fichiers)
6. [Contribution](#contribution)
7. [Licence](#licence)

## Introduction

L'Application de Gestion de Camping est une application basée sur Java conçue pour gérer divers aspects d'un site de
camping, y compris la planification des activités, la gestion des animateurs et la gestion de l'authentification des
utilisateurs. L'application utilise JavaFX pour l'interface graphique et MaterialFX pour des composants d'interface
utilisateur améliorés.

## Fonctionnalités

- **Authentification des Utilisateurs**: Système de connexion sécurisé pour les utilisateurs autorisés.
- **Planification des Activités**: Planifier et gérer les activités avec des détails tels que l'animation, l'animateur,
  le lieu, la durée, la date et l'heure.
- **Gestion des Animateurs**: Ajouter, modifier et supprimer des animateurs avec des détails comme le nom, l'email, etc.
- **Gestion des Activités**: Ajouter, modifier et supprimer des activités avec des descriptions.
- **Planification Hebdomadaire**: Visualiser et gérer les activités sur une base hebdomadaire.

## Installation

Pour installer et exécuter l'application, suivez ces étapes :

1. **Prérequis**:
    - Java Development Kit (JDK) 11 ou supérieur.
    - Apache Maven (pour la gestion des dépendances).

2. **Cloner le Dépôt**:
   ```bash
   git clone https://github.com/votre-utilisateur/votre-depot.git
   cd votre-depot

3. **Installer les Dépendances**:
   ```bash
   mvn install

4. **Exécuter l'Application**:
   ```bash
    mvn javafx:run

## Utilisation

### Connexion

Ouvrez l'application et connectez-vous avec vos identifiants.

### Accueil

Après la connexion, vous serez redirigé vers la page d'accueil où vous pouvez naviguer vers différentes sections de l'
application.

### Planification des Activités

Allez dans la section "Activité" pour planifier de nouvelles activités.
Remplissez les détails de l'activité et cliquez sur "Ajout" pour enregistrer.

### Gestion des Animateurs

Allez dans la section "Animateur" pour gérer les animateurs.
Vous pouvez ajouter, modifier ou supprimer des animateurs.

### Gestion des Activités

Allez dans la section "Activité" pour gérer les activités existantes.
Vous pouvez ajouter, modifier ou supprimer des activités.

### Planification Hebdomadaire

Allez dans la section "Accueil" pour visualiser et gérer les activités sur une base hebdomadaire.
Utilisez les boutons "<<" et ">>" pour naviguer entre les semaines.

## Structure des Fichiers

- `src/main/java/com/example/camping/`: Contient le code source de l'application.
- `src/main/resources/styles/styles.css`: Fichier CSS pour le style de l'application.
- `src/main/resources/image/`: Contient les images utilisées dans l'application.
- `pom.xml`: Fichier de configuration Maven pour la gestion des dépendances.



