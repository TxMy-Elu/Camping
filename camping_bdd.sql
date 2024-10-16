-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 17 oct. 2024 à 12:02
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

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `ajoutAct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajoutAct` (IN `id_ani` INT, IN `id_cre` INT)   BEGIN
    INSERT INTO relation1 (id_animateur, id_creneaux)
    VALUES (id_ani, id_cre);
END$$

DROP PROCEDURE IF EXISTS `ajoutCreneau`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajoutCreneau` (IN `dh` DATETIME, IN `id_animation` INT, IN `id_l` INT, IN `duree` INT, IN `id_ani` INT)   BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE last_id INT;

    WHILE i < duree DO
            INSERT INTO creneaux (date_heure, id, id_lieu,Duree)
            VALUES (dh, id_animation, id_l, duree);

            -- Récupérer le dernier ID inséré
            SET last_id = LAST_INSERT_ID();

            -- Appeler ajoutAct avec le dernier ID inséré et l'heure
            CALL ajoutAct(id_ani, last_id);

            SET dh = DATE_ADD(dh, INTERVAL 1 HOUR);
            SET i = i + 1;
        END WHILE;
END$$

--
-- Fonctions
--
DROP FUNCTION IF EXISTS `checkAjout`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `checkAjout` (`dh` DATETIME, `duree` INT) RETURNS TINYINT(1)  BEGIN
    DECLARE verif BOOLEAN DEFAULT TRUE;
    DECLARE i INT DEFAULT 0;

    WHILE i < duree DO
        IF EXISTS (SELECT * FROM creneaux WHERE date_heure = dh) THEN
            SET verif = FALSE;
        END IF;

        SET dh = DATE_ADD(dh, INTERVAL 1 HOUR);
        SET i = i + 1;
    END WHILE;
    RETURN verif;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `animateur`
--

DROP TABLE IF EXISTS `animateur`;
CREATE TABLE IF NOT EXISTS `animateur` (
  `id_animateur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_animateur`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `animateur`
--

INSERT INTO `animateur` (`id_animateur`, `nom`, `prenom`, `email`) VALUES
(1, 'Smith', 'John', 'john.smith@example.com'),
(2, 'Doe', 'Jane', 'jane.doe@example.com'),
(3, 'Brown', 'Chris', 'chris.brown@example.com'),
(4, 'DOGUET', 'Tom', 'to.doguet@gmail.com'),
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
(50, 'Guerrero', 'Noah', 'noah.guerrero@example.com'),
(52, 'Davis', 'David', 'david.newemail@example.com'),
(54, 'Doe', 'John', 'john.doe@example.com'),
(55, 'Smith', 'Jane', 'jane.smith@example.com'),
(56, 'Davis', 'David', 'david.newemail@example.com'),
(58, 'Doe', 'John', 'john.doe@example.com'),
(59, 'Smith', 'Jane', 'jane.smith@example.com'),
(60, 'Davis', 'David', 'david.newemail@example.com'),
(61, 'Evans', 'Evan', 'test@test.fr'),
(63, 'Doe', 'John', 'john.doe@example.com'),
(64, 'Smith', 'Jane', 'jane.smith@example.com'),
(65, 'Evans', 'Evan', 'test@test.fr'),
(66, 'Evans', 'Evan', 'test@test.fr'),
(67, 'Davis', 'David', 'david.newemail@example.com'),
(68, 'Evans', 'Evan', 'test@test.fr'),
(69, 'Evans', 'Evan', 'test@test.fr'),
(71, 'Doe', 'John', 'john.doe@example.com'),
(72, 'Smith', 'Jane', 'jane.smith@example.com'),
(73, 'Davis', 'David', 'david.newemail@example.com'),
(74, 'Evans', 'Evan', 'test@test.fr'),
(75, 'Evans', 'Evan', 'test@test.fr'),
(77, 'Doe', 'John', 'john.doe@example.com'),
(78, 'Smith', 'Jane', 'jane.smith@example.com'),
(79, 'Davis', 'David', 'david.newemail@example.com'),
(80, 'Evans', 'Evan', 'test@test.fr'),
(81, 'Evans', 'Evan', 'test@test.fr'),
(83, 'Doe', 'John', 'john.doe@example.com'),
(84, 'Smith', 'Jane', 'jane.smith@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `animation`
--

DROP TABLE IF EXISTS `animation`;
CREATE TABLE IF NOT EXISTS `animation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descriptif` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `login` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_comtpe`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id_comtpe`, `login`, `password`, `role`) VALUES
(3, 'admin', '&$SWjqUK8$2w', 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

DROP TABLE IF EXISTS `creneaux`;
CREATE TABLE IF NOT EXISTS `creneaux` (
  `id_creneaux` int NOT NULL AUTO_INCREMENT,
  `date_heure` datetime NOT NULL,
  `id` int NOT NULL,
  `id_lieu` int NOT NULL,
  `Duree` int NOT NULL,
  PRIMARY KEY (`id_creneaux`),
  KEY `Creneaux_animation_FK` (`id`),
  KEY `Creneaux_lieu0_FK` (`id_lieu`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `creneaux`
--

INSERT INTO `creneaux` (`id_creneaux`, `date_heure`, `id`, `id_lieu`, `Duree`) VALUES
(100, '2024-10-16 08:00:00', 4, 4, 4),
(101, '2024-10-16 09:00:00', 4, 4, 4),
(102, '2024-10-16 10:00:00', 4, 4, 4),
(103, '2024-10-16 11:00:00', 4, 4, 4),
(104, '2024-10-17 14:00:00', 16, 16, 2),
(105, '2024-10-17 15:00:00', 16, 16, 2),
(106, '2024-10-23 09:00:00', 39, 39, 3),
(107, '2024-10-23 10:00:00', 39, 39, 3),
(108, '2024-10-23 11:00:00', 39, 39, 3),
(109, '2024-10-25 15:00:00', 9, 9, 1),
(110, '2024-10-22 13:00:00', 21, 26, 2),
(111, '2024-10-22 14:00:00', 21, 26, 2),
(112, '2024-10-21 16:00:00', 20, 20, 3),
(113, '2024-10-21 17:00:00', 20, 20, 3),
(114, '2024-10-21 18:00:00', 20, 20, 3),
(115, '2024-10-24 08:00:00', 17, 18, 4),
(116, '2024-10-24 09:00:00', 17, 18, 4),
(117, '2024-10-24 10:00:00', 17, 18, 4),
(118, '2024-10-24 11:00:00', 17, 18, 4);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id_lieu` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `id_relation` int NOT NULL AUTO_INCREMENT,
  `id_animateur` int NOT NULL,
  `id_creneaux` int NOT NULL,
  PRIMARY KEY (`id_relation`),
  UNIQUE KEY `unique_animateur_creneaux` (`id_animateur`,`id_creneaux`),
  KEY `relation1_Creneaux0_FK` (`id_creneaux`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `relation1`
--

INSERT INTO `relation1` (`id_relation`, `id_animateur`, `id_creneaux`) VALUES
(85, 4, 100),
(86, 4, 101),
(87, 4, 102),
(88, 4, 103),
(89, 4, 104),
(90, 4, 105),
(91, 4, 106),
(92, 4, 107),
(93, 4, 108),
(94, 4, 109),
(95, 4, 110),
(96, 4, 111),
(97, 4, 112),
(98, 4, 113),
(99, 4, 114),
(100, 4, 115),
(101, 4, 116),
(102, 4, 117),
(103, 4, 118);

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
