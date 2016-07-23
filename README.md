WorldCup2014
============

This is a Java Swing CRUD application.



![screnshot](https://cloud.githubusercontent.com/assets/8080260/16430655/94a209f8-3d74-11e6-832c-920f707c0f5c.jpg)



The MySQL database name is `worldcup` and these are the tables:

```
  CREATE TABLE players (
    PlayerName varchar(255) NOT NULL,
    PlayerPosition varchar(45) DEFAULT NULL,
    goals varchar(10) DEFAULT NULL,
    goals_saved varchar(10) DEFAULT NULL,
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
