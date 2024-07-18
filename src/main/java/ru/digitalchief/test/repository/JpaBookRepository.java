package ru.digitalchief.test.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.test.model.Book;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaBookRepository implements BookRepository{

    @PersistenceContext
    EntityManager em;

    private final AuthorRepository authorRepository;

    @Autowired
    public JpaBookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Book save(Book book, int authorId) {
        book.setAuthor(authorRepository.get(authorId));
        if (book.isNew()) {
            em.persist(book);
            return book;
        }
        return get(book.id(), authorId) == null ? null : em.merge(book);
    }

    @Override
    @Transactional
    public boolean delete(int id, int authorId) {
        return em.createNamedQuery(Book.DELETE)
                .setParameter("id",id)
                .setParameter("authorId", authorId)
                .executeUpdate() != 0;
    }

    @Override
    public Book get(int id, int authorId) {
        Book book = em.find(Book.class, id);
        return book != null && book.getAuthor().id() == authorId ? book : null;
    }

    @Override
    public List<Book> getAllByAuthor(int authorId) {
        return em.createNamedQuery(Book.ALL_BY_AUTHOR_SORTED, Book.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    @Override
    public List<Book> getAll() {
        return em.createNamedQuery(Book.ALL_SORTED, Book.class)
                .getResultList();
    }
}
