package ru.springproject.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/all")
    public List<BookDTO> getAllBook() {
        BookDTO bookDTO = new BookDTO();
        return bookDTO.getBookDTOList(bookRepository.findAll());
    }

//    @GetMapping("/books")
//    public Page<Book> getAllBook(Pageable pageable){
//        return bookRepository.findAll(pageable);
//    }
//
////    @GetMapping("/book/{id}")
////    public Book getBookById(@PathVariable(value = "id") int bookId) {
////        return bookRepository.findById(bookId)
////                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
////    }
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
