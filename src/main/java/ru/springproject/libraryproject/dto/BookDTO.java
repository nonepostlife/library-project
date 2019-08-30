package ru.springproject.libraryproject.dto;

import org.springframework.transaction.annotation.Transactional;
import ru.springproject.libraryproject.model.Author;
import ru.springproject.libraryproject.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private int idbook;
    private String bookName;
    private int bookYearWriting;
    private List<String> authors;

    @Transactional
    public List<BookDTO> getBookDTOList(List<Book> bookList){
        List<BookDTO> bookDTOList = new ArrayList<>();

        for(Book book : bookList)
        {
            authors = new ArrayList<>();

            BookDTO bookDTO = new BookDTO();
            bookDTO.setIdBook(book.getIdBook());
            bookDTO.setBookName(book.getBookName());
            bookDTO.setBookYearWriting(book.getBookYearWriting());

            for(Author author : book.getAuthors())
            {
//                AuthorDTO authorDTO = new AuthorDTO();
//                authorDTO.setIdAuthor(author.getIdAuthor());
//                authorDTO.setAuthorName(author.getAuthorName());

                authors.add(author.getAuthorName());
            }
            bookDTO.setAuthors(authors);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
