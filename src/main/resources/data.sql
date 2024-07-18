DELETE FROM book;
DELETE FROM author;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO author (first_name, last_name, birth_date) VALUES ('George', 'Orwell', '1903-06-25');
INSERT INTO author (first_name, last_name, birth_date) VALUES ('Aldous', 'Huxley', '1894-07-26');
INSERT INTO author (first_name, last_name, birth_date) VALUES ('Jane', 'Austen', '1775-12-16');
INSERT INTO author (first_name, last_name, birth_date) VALUES ('Mark', 'Twain', '1835-11-30');
INSERT INTO author (first_name, last_name, birth_date) VALUES ('Fyodor', 'Dostoevsky', '1821-11-11');

INSERT INTO book (name, release_date, author_id) VALUES ('1984', '1949-06-08', 100000);
INSERT INTO book (name, release_date, author_id) VALUES ('Animal Farm', '1945-08-17', 100000);
INSERT INTO book (name, release_date, author_id) VALUES ('Brave New World', '1932-08-30', 100001);
INSERT INTO book (name, release_date, author_id) VALUES ('Pride and Prejudice', '1813-01-28', 100002);
INSERT INTO book (name, release_date, author_id) VALUES ('Adventures of Huckleberry Finn', '1884-12-10', 100003);
INSERT INTO book (name, release_date, author_id) VALUES ('The Brothers Karamazov', '1880-11-01', 100004);
INSERT INTO book (name, release_date, author_id) VALUES ('Crime and Punishment', '1866-01-01', 100004);
