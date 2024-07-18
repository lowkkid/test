package ru.digitalchief.test.repository;

import ru.digitalchief.test.model.Author;

import java.util.List;

public interface AuthorRepository {

    /**
     * @return null if not found, when updated
     */
    Author save(Author author);

    /**
     * @return false if not found
     */
    boolean delete(int id);

    /**
     * @return null if not found
     */
    Author get(int id);

    /**
     * @return list of authors ordered by surname, name
     */
    List<Author> getAll();
}
