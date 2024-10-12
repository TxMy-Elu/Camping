-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 10 oct. 2024 à 10:14
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

INSERT INTO `animateur` (`id_animateur`, `nom`, `prenom`, `email`) VALUES
(1, 'Smith', 'John', 'john.smith@example.com'),
(2, 'Doe', 'Jane', 'jane.doe@example.com'),
(3, 'Brown', 'Chris', 'chris.brown@example.com'),
(4, 'DOGUET', 'Tom', 'blabla@gmail.com'),
(5, 'Taylor', 'Olivia', 'olivia.taylor@example.com'),
(6, 'Wilson', 'Sophia', 'sophia.wilson@example.com'),
(7, 'Johnson', 'Emily', 'emily.johnson@example.com'),
(8, 'Thomas', 'Ava', 'ava.thomas@example.com'),
(9, 'Moore', 'Mia', 'mia.moore@example.com'),
(10, 'Anderson', 'Charlotte', 'charlotte.anderson@example.com'),
(11, 'Jackson', 'Amelia', 'amelia.jackson@example.com'),
(12, 'White', 'Harper', 'harper.white@example.com'),
(13, 'Harris', 'Evelyn', 'evelyn.harris@example.com'),
(14, 'Clark', 'Abigail', 'abigail.clark@example.com'),
(15, 'Lewis', 'Elizabeth', 'elizabeth.lewis@example.com'),
(16, 'Robinson', 'Sofia', 'sofia.robinson@example.com'),
(17, 'Walker', 'Victoria', 'victoria.walker@example.com'),
(18, 'Young', 'Grace', 'grace.young@example.com'),
(19, 'Allen', 'Chloe', 'chloe.allen@example.com'),
(20, 'King', 'Lily', 'lily.king@example.com'),
(21, 'Wright', 'Zoey', 'zoey.wright@example.com'),
(22, 'Scott', 'Hannah', 'hannah.scott@example.com'),
(23, 'Green', 'Luna', 'luna.green@example.com'),
(24, 'Martin', 'Lucas', 'lucas.martin@example.com'),
(25, 'Garcia', 'Mia', 'mia.garcia@example.com'),
(26, 'Rodriguez', 'Noah', 'noah.rodriguez@example.com'),
(27, 'Lopez', 'Sophia', 'sophia.lopez@example.com'),
(28, 'Gonzalez', 'Liam', 'liam.gonzalez@example.com'),
(29, 'Perez', 'Emma', 'emma.perez@example.com'),
(30, 'Hernandez', 'Mason', 'mason.hernandez@example.com'),
(31, 'Martinez', 'Olivia', 'olivia.martinez@example.com'),
(32, 'Morales', 'Ethan', 'ethan.morales@example.com'),
(33, 'Torres', 'Ava', 'ava.torres@example.com'),
(34, 'Ramirez', 'Sophia', 'sophia.ramirez@example.com'),
(35, 'Flores', 'Jackson', 'jackson.flores@example.com'),
(36, 'Sanchez', 'Mia', 'mia.sanchez@example.com'),
(37, 'Rivera', 'Liam', 'liam.rivera@example.com'),
(38, 'Cruz', 'Emma', 'emma.cruz@example.com'),
(39, 'Ortiz', 'Noah', 'noah.ortiz@example.com'),
(40, 'Gomez', 'Sophia', 'sophia.gomez@example.com'),
(41, 'Diaz', 'Mason', 'mason.diaz@example.com'),
(42, 'Reyes', 'Olivia', 'olivia.reyes@example.com'),
(43, 'Gutierrez', 'Ethan', 'ethan.gutierrez@example.com'),
(44, 'Cortez', 'Ava', 'ava.cortez@example.com'),
(45, 'Castillo', 'Sophia', 'sophia.castillo@example.com'),
(46, 'Jimenez', 'Jackson', 'jackson.jimenez@example.com'),
(47, 'Salazar', 'Mia', 'mia.salazar@example.com'),
(48, 'Silva', 'Liam', 'liam.silva@example.com'),
(49, 'Munoz', 'Emma', 'emma.munoz@example.com'),
(50, 'Guerrero', 'Noah', 'noah.guerrero@example.com');

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

INSERT INTO `animation` (`id`, `nom`, `descriptif`) VALUES
(1, 'Yoga Class', 'A relaxing yoga session for beginners'),
(2, 'Dance Workshop', 'An energetic dance class for all levels'),
(3, 'Cooking Class', 'Learn to cook healthy meals'),
(4, 'Painting Class', 'A creative painting session for all levels'),
(5, 'Yoga for Kids', 'A fun and engaging yoga class for children'),
(6, 'Dance Fitness', 'A high-energy dance fitness class'),
(7, 'Cooking Workshop', 'Learn to cook delicious meals'),
(8, 'Art Therapy', 'A therapeutic art session'),
(9, 'Meditation Class', 'A calming meditation session'),
(10, 'Pottery Class', 'A hands-on pottery class'),
(11, 'Zumba Class', 'A high-energy Zumba dance class'),
(12, 'Photography Workshop', 'Learn the basics of photography'),
(13, 'Gardening Workshop', 'Learn to grow your own garden'),
(14, 'Baking Class', 'Learn to bake delicious treats'),
(15, 'Sewing Class', 'Learn to sew and create your own clothes'),
(16, 'DIY Crafts', 'A fun DIY crafts session'),
(17, 'Music Lessons', 'Learn to play an instrument'),
(18, 'Language Class', 'Learn a new language'),
(19, 'First Aid Training', 'Learn basic first aid skills'),
(20, 'Computer Class', 'Learn basic computer skills'),
(21, 'Chess Club', 'Learn and play chess'),
(22, 'Book Club', 'Join a book club and discuss books'),
(23, 'Writing Workshop', 'Improve your writing skills'),
(24, 'Coding Workshop', 'Learn to code in Python'),
(25, 'Fitness Bootcamp', 'A high-intensity fitness class'),
(26, 'Flower Arranging', 'Learn to arrange beautiful flowers'),
(27, 'Wine Tasting', 'A fun and educational wine tasting session'),
(28, 'Calligraphy Class', 'Learn the art of calligraphy'),
(29, 'Salsa Dancing', 'A fun and energetic salsa dance class'),
(30, 'Basket Weaving', 'Learn to weave beautiful baskets'),
(31, 'Jewelry Making', 'Create your own unique jewelry'),
(32, 'Origami Class', 'Learn the art of paper folding'),
(33, 'Knitting Class', 'Learn to knit and create your own clothes'),
(34, 'Candle Making', 'Learn to make your own scented candles'),
(35, 'Soap Making', 'Learn to make your own natural soaps'),
(36, 'Pottery Painting', 'Paint and decorate your own pottery'),
(37, 'Mosaic Art', 'Create beautiful mosaic art pieces'),
(38, 'Leather Crafting', 'Learn to craft with leather'),
(39, 'Woodworking', 'Learn basic woodworking skills'),
(40, 'Glass Blowing', 'Learn the art of glass blowing'),
(41, 'Metalworking', 'Learn basic metalworking skills'),
(42, 'Beading Class', 'Create beautiful beaded jewelry'),
(43, 'Embroidery Class', 'Learn the art of embroidery'),
(44, 'Quilting Class', 'Learn to make your own quilts'),
(45, 'Macrame Class', 'Learn the art of macrame'),
(46, 'Tie-Dye Workshop', 'Create your own tie-dye clothes'),
(47, 'Screen Printing', 'Learn to screen print your own designs'),
(48, 'Stained Glass', 'Create beautiful stained glass art'),
(49, 'Ceramic Painting', 'Paint and decorate your own ceramics'),
(50, 'Resin Art', 'Create beautiful resin art pieces');

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

INSERT INTO `compte` (`id_comtpe`, `login`, `password`, `role`) VALUES
(1, 'jsmith', 'password123', 'Instructor'),
(2, 'jdoe', 'securePass', 'Coordinator'),
(3, 'admin', '&$SWjqUK8$2w', 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

DROP TABLE IF EXISTS `creneaux`;
CREATE TABLE IF NOT EXISTS `creneaux` (
  `id_creneaux` int NOT NULL AUTO_INCREMENT,
  `date_heure` datetime NOT NULL,
  `lieu` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `id` int NOT NULL,
  `id_lieu` int NOT NULL,
  PRIMARY KEY (`id_creneaux`),
  KEY `Creneaux_animation_FK` (`id`),
  KEY `Creneaux_lieu0_FK` (`id_lieu`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `creneaux`
--

INSERT INTO `creneaux` (`id_creneaux`, `date_heure`, `lieu`, `id`, `id_lieu`) VALUES
(2, '2024-09-02 14:00:00', 'Studio Room 1', 2, 2),
(3, '2024-09-03 16:00:00', 'Outdoor Garden', 3, 3),
(4, '2024-09-04 10:00:00', 'Art Studio', 4, 4),
(5, '2024-09-05 14:00:00', 'Yoga Room', 5, 5),
(6, '2024-09-06 16:00:00', 'Dance Studio', 6, 6),
(9, '2024-09-09 16:00:00', 'Meditation Room', 9, 9),
(10, '2024-09-10 10:00:00', 'Pottery Studio', 10, 10),
(11, '2024-09-11 14:00:00', 'Zumba Room', 11, 11),
(12, '2024-09-12 16:00:00', 'Photography Studio', 12, 12),
(13, '2024-09-13 10:00:00', 'Garden', 13, 13),
(16, '2024-09-16 10:00:00', 'DIY Crafts Room', 16, 16),
(17, '2024-09-17 14:00:00', 'Music Room', 17, 17),
(18, '2024-09-18 16:00:00', 'Language Classroom', 18, 18),
(19, '2024-09-19 10:00:00', 'First Aid Room', 19, 19),
(20, '2024-09-20 14:00:00', 'Computer Lab', 20, 20),
(23, '2024-09-23 14:00:00', 'Writing Workshop Room', 23, 23),
(24, '2024-09-24 10:00:00', 'Coding Room', 24, 24),
(25, '2024-09-25 14:00:00', 'Fitness Studio', 25, 25),
(26, '2024-09-26 16:00:00', 'Flower Garden', 26, 26),
(27, '2024-09-27 10:00:00', 'Wine Tasting Room', 27, 27),
(30, '2024-09-30 10:00:00', 'Basket Weaving Room', 30, 30),
(31, '2024-10-01 14:00:00', 'Jewelry Making Room', 31, 31),
(32, '2024-10-02 16:00:00', 'Origami Room', 32, 32),
(33, '2024-10-03 10:00:00', 'Knitting Room', 33, 33),
(34, '2024-10-04 14:00:00', 'Candle Making Room', 34, 34),
(37, '2024-10-07 14:00:00', 'Mosaic Art Room', 37, 37),
(38, '2024-10-08 16:00:00', 'Leather Crafting Room', 38, 38),
(39, '2024-10-09 10:00:00', 'Woodworking Room', 39, 39),
(40, '2024-10-10 14:00:00', 'Glass Blowing Room', 40, 40),
(41, '2024-10-11 16:00:00', 'Metalworking Room', 41, 41),
(44, '2024-10-14 16:00:00', 'Quilting Room', 44, 44),
(45, '2024-10-15 10:00:00', 'Macrame Room', 45, 45),
(46, '2024-10-16 14:00:00', 'Tie-Dye Room', 46, 46),
(47, '2024-10-17 16:00:00', 'Screen Printing Room', 47, 47),
(48, '2024-10-18 10:00:00', 'Stained Glass Room', 48, 48);

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

INSERT INTO `lieu` (`id_lieu`, `libelle`) VALUES
(1, 'Main Hall'),
(2, 'Studio Room 1'),
(3, 'Outdoor Garden'),
(4, 'Art Studio'),
(5, 'Yoga Room'),
(6, 'Dance Studio'),
(7, 'Kitchen'),
(8, 'Art Therapy Room'),
(9, 'Meditation Room'),
(10, 'Pottery Studio'),
(11, 'Zumba Room'),
(12, 'Photography Studio'),
(13, 'Garden'),
(14, 'Baking Kitchen'),
(15, 'Sewing Room'),
(16, 'DIY Crafts Room'),
(17, 'Music Room'),
(18, 'Language Classroom'),
(19, 'First Aid Room'),
(20, 'Computer Lab'),
(21, 'Chess Room'),
(22, 'Book Club Room'),
(23, 'Writing Workshop Room'),
(24, 'Coding Room'),
(25, 'Fitness Studio'),
(26, 'Flower Garden'),
(27, 'Wine Tasting Room'),
(28, 'Calligraphy Room'),
(29, 'Salsa Dance Studio'),
(30, 'Basket Weaving Room'),
(31, 'Jewelry Making Room'),
(32, 'Origami Room'),
(33, 'Knitting Room'),
(34, 'Candle Making Room'),
(35, 'Soap Making Room'),
(36, 'Pottery Painting Room'),
(37, 'Mosaic Art Room'),
(38, 'Leather Crafting Room'),
(39, 'Woodworking Room'),
(40, 'Glass Blowing Room'),
(41, 'Metalworking Room'),
(42, 'Beading Room'),
(43, 'Embroidery Room'),
(44, 'Quilting Room'),
(45, 'Macrame Room'),
(46, 'Tie-Dye Room'),
(47, 'Screen Printing Room'),
(48, 'Stained Glass Room'),
(49, 'Ceramic Painting Room'),
(50, 'Resin Art Room');

-- --------------------------------------------------------

--
-- Structure de la table `relation1`
--

DROP TABLE IF EXISTS `relation1`;
CREATE TABLE IF NOT EXISTS `relation1` (
  `id_relation` INT NOT NULL AUTO_INCREMENT,
  `id_animateur` int NOT NULL,
  `id_creneaux` int NOT NULL,
  PRIMARY KEY (`id_relation`),
  KEY `relation1_Creneaux0_FK` (`id_creneaux`),
  UNIQUE KEY `unique_animateur_creneaux` (`id_animateur`, `id_creneaux`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `relation1`
--

INSERT INTO `relation1` (`id_relation`, `id_animateur`, `id_creneaux`) VALUES
(1, 2, 2),
(2, 3, 3),
(3, 4, 4),
(4, 5, 5),
(5, 6, 6),
(6, 9, 9),
(7, 10, 10),
(8, 11, 11),
(9, 12, 12),
(10, 13, 13),
(11, 16, 16),
(12, 17, 17),
(13, 18, 18),
(14, 19, 19),
(15, 20, 20),
(16, 23, 23),
(17, 24, 24),
(18, 25, 25),
(19, 26, 26),
(20, 27, 27),
(21, 30, 30),
(22, 31, 31),
(23, 32, 32),
(24, 33, 33),
(25, 34, 34),
(26, 37, 37),
(27, 38, 38),
(28, 39, 39),
(29, 40, 40),
(30, 41, 41),
(31, 44, 44),
(32, 45, 45),
(33, 46, 46),
(34, 47, 47),
(35, 48, 48);

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
