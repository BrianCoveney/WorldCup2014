WorldCup2014
============

This is a Java Swing CRUD application, and these are the MySql tables:


```
  CREATE TABLE players (
    PlayerName varchar(255) NOT NULL,
    PlayerPosition varchar(45) DEFAULT NULL,
    goals varchar(10) DEFAULT NULL,
    goals_saved varchar(10) DEFAULT NULL
    PRIMARY KEY (PlayerName)
  ) ENGINE=InnoDB;
```

```
  CREATE TABLE team_info (
    PlayerName varchar(255) DEFAULT NULL,
    TeamName varchar(255) DEFAULT NULL,
    GamesWon varchar(255) DEFAULT NULL
  ) ENGINE=InnoDB;
```
