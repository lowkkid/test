DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE author
(
    id  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name  VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    birth_date TIMESTAMP NOT NULL
);

CREATE TABLE book
(
    id  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    release_date TIMESTAMP NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);