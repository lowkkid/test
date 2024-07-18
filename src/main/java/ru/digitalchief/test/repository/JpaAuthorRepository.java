package ru.digitalchief.test.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.test.model.Author;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaAuthorRepository implements AuthorRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.isNew()) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Author.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Author get(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        return em.createNamedQuery(Author.ALL_SORTED, Author.class)
                .getResultList();
    }
}
