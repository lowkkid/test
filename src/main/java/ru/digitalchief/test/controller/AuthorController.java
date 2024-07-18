package ru.digitalchief.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalchief.test.model.Author;
import ru.digitalchief.test.model.Book;
import ru.digitalchief.test.repository.AuthorRepository;
import ru.digitalchief.test.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable int id) {
        List<Book> books = bookRepository.getAllByAuthor(id);
        return books != null ? ResponseEntity.ok(books) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable int id) {
        Author author = authorRepository.get(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author created = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@RequestBody Author author, @PathVariable int id) {
        if (author.isNew() || author.id() != id) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Author updated = authorRepository.save(author);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        authorRepository.delete(id);
    }
}
