package ru.springproject.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.springproject.libraryproject.dto.AuthorDTO;
import ru.springproject.libraryproject.dto.BookDTO;
import ru.springproject.libraryproject.repository.BookRepository;
import ru.springproject.libraryproject.exception.ResourceNotFoundException;
import ru.springproject.libraryproject.model.Book;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/all/page/{numPage}")
    public List<BookDTO> getAllBook(@PathVariable(value = "numPage") int numPage) {
        Pageable pageWithFiveElements = PageRequest.of(numPage, 5);
        Page <Book> pBook = bookRepository.findAll(pageWithFiveElements);
        List<Book> lBook = pBook.getContent();
        BookDTO bookDTO = new BookDTO();
        return bookDTO.getBookDTOList(lBook);
    }

//    @GetMapping("/search/{year}")
//    public List<BookDTO> getBooksAtYear(@PathVariable(value = "year") int year) {
//        BookDTO bookDTO = new BookDTO();
//        return bookDTO.getBookDTOList(bookRepository.findByBookYearWritingEquals(year));
//    }

    @GetMapping("/page/{num}")
    public List<BookDTO> getBooksPage(@PathVariable(value = "num") int num) {
        Pageable pageWithFiveElements = PageRequest.of(num, 5);
        BookDTO bookDTO = new BookDTO();
        return bookDTO.getBookDTOList(bookRepository.findAllByBookYearWritingGreaterThan(1900, pageWithFiveElements));
    }

//    @GetMapping("/get/{id}")
//    public Book getBookById(@PathVariable(value = "id") int bookId) {
//        Optional<Book> currentBook = bookRepository.findById(bookId);
//        BookDTO bookDTO = new BookDTO();
//        return bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//    }

//    @GetMapping("/books")
//    public Page<Book> getAllBook(Pageable pageable){
//        return bookRepository.findAll(pageable);
//    }
//
//    @GetMapping("/book/{id}")
//    public Book getBookById(@PathVariable(value = "id") int bookId) {
//        return bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//    }
//
//    @PostMapping("/books")
//    public Book createBook(@Valid @RequestBody Book book){
//        return bookRepository.save(book);
//    }
//
//    @PutMapping("/books/{id}")
//    public Book updateBook(@PathVariable(value = "id") int bookId,
//                           @Valid @RequestBody Book bookDetails){
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//
//        book.setBookName(bookDetails.getBookName());
//        book.setBookYearWriting(bookDetails.getBookYearWriting());
//
//        Book updateBook = bookRepository.save(book);
//        return updateBook;
//    }
//
//    @DeleteMapping("/books/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") int bookId){
//        return bookRepository.findById(bookId).map(book -> {
//            bookRepository.delete(book);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() ->  new ResourceNotFoundException("Book", "id", bookId));
//

//        bookRepository.deleteById(bookId);
//        return ResponseEntity.ok().build();
//    }
}
