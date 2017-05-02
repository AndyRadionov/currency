DROP TABLE IF EXISTS currencies;
--CREATE TABLE users (
--  id INT NOT NULL AUTO_INCREMENT,
--  first_name VARCHAR(30) NOT NULL,
--  last_name VARCHAR(30) NOT NULL,
--  login VARCHAR(20) NOT NULL,
--  password VARBINARY(64) NOT NULL,
--  salt VARBINARY(16) NOT NULL,
--  time_registered TIMESTAMP NOT NULL DEFAULT now(),
--  status ENUM('ACTIVE', 'DELETED') NOT NULL,
--  PRIMARY KEY (id),
--  UNIQUE login (login),
--  KEY first_name (first_name),
--  KEY last_name (last_name)
--) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

CREATE TABLE currencies (
  id SERIAL PRIMARY KEY NOT NULL,
  base CHAR(3) NOT NULL,
  code CHAR(3) NOT NULL,
  rate REAL NOT NULL,
  update_time TIMESTAMP NOT NULL,
);