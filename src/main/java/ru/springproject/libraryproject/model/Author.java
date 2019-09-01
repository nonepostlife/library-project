package ru.springproject.libraryproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Author")
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idauthor;

    @NotNull
    @Size(max = 100)
    @Column(name = "authorName")
    private String authorName;

    @ManyToMany(fetch = FetchType.LAZY,    //LAZY, EAGER
            cascade = {
                    CascadeType.ALL,        //PERSIST, MERGE, ALL
            },
            mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author() { }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(int idauthor, String authorName) {
        this.idauthor = idauthor;
        this.authorName = authorName;
    }

    public int getIdAuthor() {
        return idauthor;
    }

    public void setIdAuthor(int idauthor) {
        this.idauthor = idauthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString(){
        return String.format("[id = %s, author = %s]", idauthor, authorName);
    }
}
