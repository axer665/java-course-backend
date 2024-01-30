CREATE SEQUENCE user_sequence START 3 INCREMENT 1;

CREATE TABLE if not exists users(
    id int8 primary key not null,
    email varchar,
    password varchar,
    first_name varchar,
    last_name varchar
);

insert into users(id, email, password, first_name, last_name)
values (1, 'check@email.ru', 'check', 'Михаил', 'Стройнов');
insert into users(id, email, password, first_name, last_name)
values (2, 'test@email.ru', 'test', 'Test', 'Test');


CREATE SEQUENCE file_sequence START 1 INCREMENT 1;

CREATE TABLE if not exists files(
    id int8 primary key not null,
    file_name varchar,
    size int8,
    type varchar,
    body bytea
);