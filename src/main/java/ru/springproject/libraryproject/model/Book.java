package ru.springproject.libraryproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idbook;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String bookName;
    private int bookYearWriting;

    @ManyToMany(fetch = FetchType.LAZY,    // EAGER, LAZY
            cascade = {
                    CascadeType.ALL,        // ALL, PERSIST, MERGE
            })
    @JoinTable(name = "author_book",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns  = { @JoinColumn(name = "author_id") }
    )
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String bookName, int bookYearWriting) {
        this.bookName = bookName;
        this.bookYearWriting = bookYearWriting;
    }

    public Book(int idbook, String bookName, int bookYearWriting) {
        this.idbook = idbook;
        this.bookName = bookName;
        this.bookYearWriting = bookYearWriting;
    }

    public int getIdBook() {
        return idbook;
    }

    public void setIdBook(int idbook) {
        this.idbook = idbook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookYearWriting() {
        return bookYearWriting;
    }

    public void setBookYearWriting(int bookYearWriting) {
        this.bookYearWriting = bookYearWriting;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    @Override
    public String toString() {
        return String.format("[id = %s, name = %s, year = %s]", idbook, bookName, bookYearWriting);
    }
}
