package ru.digitalchief.test.repository;

import ru.digitalchief.test.model.Book;

import java.util.List;

public interface BookRepository {

    /**
     * @return null, if updated dish does not belong to authorId.
     */
    Book save(Book book, int authorId);

    /**
     * @return false if book does not belong to authorId.
     */
    boolean delete(int id, int authorId);

    /**
     * @return null if book does not belong to authorId.
     */
    Book get(int id, int authorId);

    /**
     * @return list of author's books ordered by name.
     */
    List<Book> getAllByAuthor(int authorId);

    /**
     * @return all books ordered by name
     */
    List<Book> getAll();
}
