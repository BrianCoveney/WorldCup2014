WorldCup2014
============

This is a CRUD application created with Java, which is used in conjunction with the below MySql tables.

CREATE TABLE `players` (
  `name` varchar(255) NOT NULL,
  `goals` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
