DROP TABLE IF EXISTS currencies;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id SERIAL PRIMARY KEY NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  login VARCHAR(20) unique NOT NULL,
  password bytea NOT NULL,
  salt bytea NOT NULL,
  time_registered TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE user_roles (
  user_login VARCHAR(20) NOT NULL,
  role    VARCHAR(10),
  CONSTRAINT user_roles_login UNIQUE (user_login, role),
  FOREIGN KEY (user_login) REFERENCES users (login) ON DELETE CASCADE
);

CREATE TABLE currencies (
  id SERIAL PRIMARY KEY NOT NULL,
  base CHAR(3) NOT NULL,
  code CHAR(3) NOT NULL,
  rate REAL NOT NULL,
  update_time TIMESTAMP NOT NULL
);
