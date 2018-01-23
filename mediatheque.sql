-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 23 Janvier 2018 à 14:18
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `mediatheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `idCategory` bigint(20) NOT NULL,
  `nameCategory` varchar(10) NOT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `UK88f8gjni450ps1eb0q4e5tvtw` (`nameCategory`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`idCategory`, `nameCategory`) VALUES
(1, 'INFO'),
(2, 'HISTO'),
(3, 'INFOR'),
(4, 'INFORMAT');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(31),
(31);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `idItem` bigint(20) NOT NULL,
  `duration` int(11) NOT NULL,
  `numberPages` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `releaseDate` date NOT NULL,
  `summary` varchar(250) NOT NULL,
  `title` varchar(60) NOT NULL,
  `universalProductCode` bigint(20) NOT NULL,
  `category_idCategory` bigint(20) NOT NULL,
  `typeItem_idTypeItem` bigint(20) NOT NULL,
  PRIMARY KEY (`idItem`),
  KEY `FK4u0brfpdu3xth7a6pwr57axfh` (`category_idCategory`),
  KEY `FK1r9h1gy6o7os4iocpoa7qcxa4` (`typeItem_idTypeItem`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `item`
--

INSERT INTO `item` (`idItem`, `duration`, `numberPages`, `quantity`, `releaseDate`, `summary`, `title`, `universalProductCode`, `category_idCategory`, `typeItem_idTypeItem`) VALUES
(18, 0, 1500, 100, '1994-05-15', 'resume 12', 'titre 12', 123455, 1, 1),
(20, 120, 0, 10, '1999-02-02', 'Je suis un résumé', 'TITRE WAKE', 122334, 1, 1),
(21, 0, 1300, 15, '1998-03-09', 'Je suis un autre résumé mais pas plus développé.', 'TITRE TITRE', 177889, 1, 2),
(22, 30, 0, 7, '1997-01-25', 'Je suis encore un autre résumé je n''ai pas vraiment d''imagination', 'AUTRE TITRE', 654312, 3, 1),
(23, 0, 1645, 89, '2007-12-31', 'Je suis pas imaginatif, désolé', 'TITRE OUI TITRE', 456987, 4, 2),
(24, 6, 0, 1, '2005-11-11', 'JE suis un ... dauphin.23', 'JE SUIS TITRE23', 456951, 3, 2),
(25, 0, 1468, 13, '2004-07-24', 'oizujzeo ioz jeziioe jzoi ejfizefi zjefiufhzefzefh ', 'Ok je suis perdu', 134489, 2, 2),
(26, 0, 1654, 658, '2001-05-17', 'Encore un autre résumé, là j''en ai marre', 'ENCORE UN TITRE', 318497, 1, 2),
(27, 145, 0, 15, '1567-02-17', 'ok pas ok pas ok ok ok pas pas', 'MOI TITRE MOI', 897465, 3, 1),
(28, 0, 1465, 15, '1236-03-03', 'zze ez zf  fefzef zef eggzregg tgr g eg er gerg', 'oui titre non', 164879, 4, 2),
(29, 145, 0, 23, '2001-01-22', 'zefezf 15144 897897z  zf zfzef zffvbtrhtyh 987', 'titre peut être', 154823, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `idRole` bigint(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `UK7d8a768x6aiuvmsa24hqiharg` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`idRole`, `name`) VALUES
(1, 'ADMIN'),
(2, 'EMP'),
(3, 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `typeitem`
--

CREATE TABLE IF NOT EXISTS `typeitem` (
  `idTypeItem` bigint(20) NOT NULL,
  `nameItem` varchar(10) NOT NULL,
  PRIMARY KEY (`idTypeItem`),
  UNIQUE KEY `UK6pbsue7e5v80o8bnuux99x9vl` (`nameItem`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typeitem`
--

INSERT INTO `typeitem` (`idTypeItem`, `nameItem`) VALUES
(1, 'CD'),
(2, 'PAPER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `role_idRole` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK587tdsv8u5cvheyo9i261xhry` (`login`),
  KEY `FKa49opdrm742csh6w190acttmx` (`role_idRole`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`idUser`, `email`, `firstName`, `lastName`, `login`, `password`, `role_idRole`) VALUES
(10, 'brenoit.mickael@gmail.com', 'Mickael', 'BRENOIT', 'framboise', '$2a$10$nHjY4vadPfsdjN5wjEgRheNGgPxQdqYn5mcgqAiuzbjaCoSRzGLdi', 2),
(11, 'brenoit.mickael@gmail.com', 'Mickael', 'BRENOIT', 'bob', '$2a$10$q5bqH70phiuntpXCRQjvYupFqESp88XZrl1RB9iQCYGxYlcTQyHTS', 1),
(12, 'brenoit.mickael@gmail.com', 'Mickael', 'BRENOIT', 'Fraise', '$2a$10$EYB.waKvPjhbeLryjN/w7e/T7lvAs6C5YqK6PYtLx9kuY1RYlSTai', 3),
(14, 'brenoit.mickael@gmail.com', 'Pierre', 'Dupont', 'PierreD', '$2a$10$TizH6C7F/QxwzsLRRVGvGedJQyelRQGDR3oaR6uoqgjv4Ex20Gpwa', 2),
(15, 'sylalb@gmail.com', 'Sylvain', 'ALB', 'Phoque', '$2a$10$789YtTxiVsUwlJ7Q4.WPXegkmtHehQQIa15K9O3QzgV0cZTmG7tIy', 3),
(30, 'email21012018@gmail.com', 'Prenom21012018', 'Nom21012018', 'framboise21012018', '$2a$10$Zgv6eWnFSOR36U3ONfDl8O9Nji6KLCLS/JqMWSRvJQnjf5x5qMrl6', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
