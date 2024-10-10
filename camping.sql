    -- phpMyAdmin SQL Dump
    -- version 5.2.1
    -- https://www.phpmyadmin.net/
    --
    -- Hôte : 127.0.0.1:3306
    -- Généré le : mar. 01 oct. 2024 à 09:07
    -- Version du serveur : 8.3.0
    -- Version de PHP : 8.2.18

    SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
    START TRANSACTION;
    SET time_zone = "+00:00";


    /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
    /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
    /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
    /*!40101 SET NAMES utf8mb4 */;

    --
    -- Base de données : `camping`
    --

    -- --------------------------------------------------------

    --
    -- Structure de la table `animateur`
    --

    DROP TABLE IF EXISTS `animateur`;
    CREATE TABLE IF NOT EXISTS `animateur` (
      `id_animateur` int NOT NULL AUTO_INCREMENT,
      `nom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
      `prenom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
      `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
      PRIMARY KEY (`id_animateur`)
    ) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `animateur`
    --

    -- --------------------------------------------------------

    --
    -- Structure de la table `animation`
    --

    DROP TABLE IF EXISTS `animation`;
    CREATE TABLE IF NOT EXISTS `animation` (
      `id` int NOT NULL AUTO_INCREMENT,
      `nom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
      `descriptif` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `animation`
    --

    -- --------------------------------------------------------

    --
    -- Structure de la table `compte`
    --

    DROP TABLE IF EXISTS `compte`;
    CREATE TABLE IF NOT EXISTS `compte` (
      `id_comtpe` int NOT NULL AUTO_INCREMENT,
      `login` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
      `password` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
      `role` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
      PRIMARY KEY (`id_comtpe`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `compte`
    --


    -- --------------------------------------------------------

    --
    -- Structure de la table `creneaux`
    --

    DROP TABLE IF EXISTS `creneaux`;
    CREATE TABLE IF NOT EXISTS `creneaux` (
      `id_creneaux` int NOT NULL AUTO_INCREMENT,
      `date_heure` datetime NOT NULL,
      `lieu` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
      `duree` int NOT NULL,
      `id` int NOT NULL,
      `id_lieu` int NOT NULL,
      PRIMARY KEY (`id_creneaux`),
      KEY `Creneaux_animation_FK` (`id`),
      KEY `Creneaux_lieu0_FK` (`id_lieu`)
    ) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `creneaux`
    --

    -- --------------------------------------------------------

    --
    -- Structure de la table `lieu`
    --

    DROP TABLE IF EXISTS `lieu`;
    CREATE TABLE IF NOT EXISTS `lieu` (
      `id_lieu` int NOT NULL AUTO_INCREMENT,
      `libelle` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
      PRIMARY KEY (`id_lieu`)
    ) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `lieu`
    --

    -- --------------------------------------------------------

    --
    -- Structure de la table `relation1`
    --

    DROP TABLE IF EXISTS `relation1`;
    CREATE TABLE IF NOT EXISTS `relation1` (
      `id_animateur` int NOT NULL,
      `id_creneaux` int NOT NULL,
      PRIMARY KEY (`id_animateur`,`id_creneaux`),
      KEY `relation1_Creneaux0_FK` (`id_creneaux`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

    --
    -- Déchargement des données de la table `relation1`
    --


    --
    -- Contraintes pour les tables déchargées
    --

    --
    -- Contraintes pour la table `creneaux`
    --
    ALTER TABLE `creneaux`
      ADD CONSTRAINT `Creneaux_animation_FK` FOREIGN KEY (`id`) REFERENCES `animation` (`id`),
      ADD CONSTRAINT `Creneaux_lieu0_FK` FOREIGN KEY (`id_lieu`) REFERENCES `lieu` (`id_lieu`);

    --
    -- Contraintes pour la table `relation1`
    --
    ALTER TABLE `relation1`
      ADD CONSTRAINT `relation1_animateur_FK` FOREIGN KEY (`id_animateur`) REFERENCES `animateur` (`id_animateur`),
      ADD CONSTRAINT `relation1_Creneaux0_FK` FOREIGN KEY (`id_creneaux`) REFERENCES `creneaux` (`id_creneaux`);
    COMMIT;

    /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
    /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
    /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
