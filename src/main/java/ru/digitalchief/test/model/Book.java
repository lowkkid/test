package ru.digitalchief.test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Book.ALL_BY_AUTHOR_SORTED, query = "SELECT b FROM Book b WHERE b.author.id=:authorId ORDER BY b.name"),
        @NamedQuery(name = Book.ALL_SORTED, query = "SELECT b FROM Book b ORDER BY b.name"),
        @NamedQuery(name = Book.DELETE, query = "DELETE FROM Book b WHERE b.id=:id AND b.author.id=:authorId"),
})
@Entity
@Table(name = "book")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book extends AbstractEntity {

    public static final String ALL_SORTED = "Book.getAll";

    public static final String ALL_BY_AUTHOR_SORTED = "Book.getAllByAuthor";
    public static final String DELETE = "Book.delete";

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "release_date", nullable = false)
    @NotNull
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull
    @JsonBackReference
    private Author author;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
