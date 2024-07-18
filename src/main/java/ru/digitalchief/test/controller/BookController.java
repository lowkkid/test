package ru.digitalchief.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalchief.test.model.Book;
import ru.digitalchief.test.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable int id, @RequestParam int authorId) {
        Book book = bookRepository.get(id, authorId);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestParam int authorId) {
        Book created = bookRepository.save(book, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable int id, @RequestParam int authorId) {
        if (book.isNew() || book.id() != id) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Book updated = bookRepository.save(book, authorId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam int authorId) {
        bookRepository.delete(id, authorId);
    }
}

