package ru.springproject.libraryproject.dto;

import org.springframework.transaction.annotation.Transactional;
import ru.springproject.libraryproject.model.Author;
import ru.springproject.libraryproject.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private int idauthor;
    private String authorName;
    private List<BookDTO> books;

    @Transactional
    public List<AuthorDTO> getAuthorDTOList(List<Author> authorList){
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for(Author author : authorList)
        {
            books = new ArrayList<>();
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setIdAuthor(author.getIdAuthor());
            authorDTO.setAuthorName(author.getAuthorName());

            for(Book book : author.getBooks())
            {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setIdBook(book.getIdBook());
                bookDTO.setBookName(book.getBookName());
                bookDTO.setBookYearWriting(book.getBookYearWriting());
                books.add(bookDTO);
            }
            authorDTO.setBooks(books);
            authorDTOList.add(authorDTO);
        }
        return authorDTOList;
    }

    @Transactional
    public AuthorDTO getAuthorDTO(Author author)
    {
        books = new ArrayList<>();
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setIdAuthor(author.getIdAuthor());
        authorDTO.setAuthorName(author.getAuthorName());

        for(Book book : author.getBooks())
        {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setIdBook(book.getIdBook());
            bookDTO.setBookName(book.getBookName());
            bookDTO.setBookYearWriting(book.getBookYearWriting());
            books.add(bookDTO);
        }
        authorDTO.setBooks(books);
        return authorDTO;
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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
