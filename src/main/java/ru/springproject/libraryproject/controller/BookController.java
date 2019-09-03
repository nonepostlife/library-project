package ru.springproject.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.springproject.libraryproject.dto.BookDTO;
import ru.springproject.libraryproject.repository.BookRepository;
import ru.springproject.libraryproject.exception.ResourceNotFoundException;
import ru.springproject.libraryproject.model.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    private final int countElementOnPage = 10;

    @GetMapping("/page/{numPage}")
    public List<BookDTO> getAllBooks(@PathVariable(value = "numPage") int numPage) {
        Pageable page = PageRequest.of(numPage, countElementOnPage);
        Page <Book> pBook = bookRepository.findAll(page);
        List<Book> lBook = pBook.getContent();
        BookDTO bookDTO = new BookDTO();
        return bookDTO.getBookDTOList(lBook);
    }

    @GetMapping("/search/{numPage}")
    public List<BookDTO> getAllBooksOnRequest(@PathVariable(value = "numPage") int numPage,
                                              @RequestParam(name = "text", required = false) String text,
                                              @RequestParam(name = "yearWriting",required = false) Integer yearWriting,
                                              @RequestParam(name = "countAuthors", required = false) Long countAuthors)
    {
        Pageable page = PageRequest.of(numPage, countElementOnPage);
        BookDTO bookDTO = new BookDTO();

        // без сортировки
        if(text == null && yearWriting == null && countAuthors == null) {
            Page<Book> pBook = bookRepository.findAll(page);
            List<Book> lBook = pBook.getContent();
            return bookDTO.getBookDTOList(lBook);
        }
        // по году
        else if(text == null && yearWriting != null && countAuthors == null){
            return bookDTO.getBookDTOList(bookRepository.findAllByBookYearWritingIs(yearWriting, page));
        }
        // по названию книги или имени автора
        else if(text != null && yearWriting == null && countAuthors == null){
            return bookDTO.getBookDTOList(bookRepository.findAllByBookNameOrAuthorNameContaining(text, text, page));
        }
        // по количеству авторов
        else if(text == null && yearWriting == null && countAuthors != null){
            return bookDTO.getBookDTOList(bookRepository.findAllByAuthorsCountIs(countAuthors, page));
        }
        // год и имя
        else if(text != null && yearWriting != null && countAuthors == null)
        {
            return bookDTO.getBookDTOList(bookRepository.findAllByBookYearWritingIsAndBookNameOrAuthorNameContaining(yearWriting, text, text, page));
        }
        // год и авторы
        else if(text == null && yearWriting != null && countAuthors != null)
        {
            return bookDTO.getBookDTOList(bookRepository.findAllByBookYearWritingIsAndAuthorsCountIs(yearWriting, countAuthors, page));
        }
        // имя и авторы
        else if(text != null && yearWriting == null && countAuthors != null)
        {
            return bookDTO.getBookDTOList(bookRepository.findByBookNameOrAuthorNameContainingAndAuthorCountIs(text, text, countAuthors, page));
        }
        // год и имя и авторы
        else if(text != null && yearWriting != null && countAuthors != null)
        {
            return bookDTO.getBookDTOList(bookRepository.findByBookYearWritingIsAndBookNameOrAuthorNameContainingAndAuthorCountIs(yearWriting, text, text, countAuthors, page));
        }
        else
            return null;
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable(value = "id") int bookId,
                           @RequestBody Book bookDetail) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setBookName(bookDetail.getBookName());
        book.setBookYearWriting(bookDetail.getBookYearWriting());

        Book updateBook = bookRepository.save(book);
        return updateBook;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeBook(@PathVariable(value = "id") int bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }
}
