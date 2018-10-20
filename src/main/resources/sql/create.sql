CREATE TABLE user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  surname VARCHAR(64) NOT NULL,
  username VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  profile VARCHAR(16) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE task (
  id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(32) NOT NULL,
  description VARCHAR(512),
  creation DATETIME NOT NULL,
  last_update DATETIME NOT NULL,
  removal DATETIME NOT NULL,
  conclusion DATETIME NOT NULL,
  user_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_task_user (user_id),
  CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE VIEW vi_auth AS SELECT username, profile FROM user;