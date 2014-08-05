WorldCup2014
============

This is a CRUD application created with Java, which is used with the below MySql tables.

CREATE TABLE `players` (
  `name` varchar(255) NOT NULL,
  `goals` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB;

CREATE TABLE `team_info` (
  `PlayerName` varchar(255) DEFAULT NULL,
  `TeamName` varchar(255) DEFAULT NULL,
  `GamesWon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;
