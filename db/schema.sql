CREATE TABLE accidents (
  id serial primary key,
  name varchar(2000),
  text varchar(2000),
  address varchar(2000),
  type_id INT REFERENCES accidents_type (id)
);

CREATE TABLE accidents_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

INSERT INTO accidents_type (name) VALUES ('Две машины');
INSERT INTO accidents_type (name) VALUES ('Машина и человек');
INSERT INTO accidents_type (name) VALUES ('Машина и велосипед');

CREATE TABLE rules (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000),
);

INSERT INTO rules (name) VALUES ('Статья 1');
INSERT INTO rules (name) VALUES ('Статья 2');
INSERT INTO rules (name) VALUES ('Статья 3');

CREATE TABLE accidents_rules (
    id SERIAL PRIMARY KEY,
    rules_id INT REFERENCES rules (id),
    accident_id INT REFERENCES accidents (id)
);

CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  PRIMARY KEY (username)
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);