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
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

CREATE TABLE authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

insert into users (username, password, authority_id)
values ('root', '$2a$10$Xq9Hih0rYhzq3IV/47nzueegubhF72EoHWKGjTlYiE4D6UpWLKPzS',
(select id from authorities where authority = 'ROLE_ADMIN'));