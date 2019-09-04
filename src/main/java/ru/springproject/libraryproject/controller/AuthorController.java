package ru.springproject.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.springproject.libraryproject.dto.AuthorDTO;
import ru.springproject.libraryproject.exception.ResourceNotFoundException;
import ru.springproject.libraryproject.model.Author;
import ru.springproject.libraryproject.model.Book;
import ru.springproject.libraryproject.repository.AuthorRepository;
import ru.springproject.libraryproject.repository.BookRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/all")
    public List<AuthorDTO> getAllAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        return authorDTO.getAuthorDTOList(authorRepository.findAll());
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable(value = "id") int authorId) {
        Author currentAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        AuthorDTO authorDTO = new AuthorDTO();
        return authorDTO.getAuthorDTO(currentAuthor);
    }

    @PostMapping("/add")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/update/{id}")
    public Author updateAuthor(@PathVariable(value = "id") int authorId,
                           @Valid @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setAuthorName(authorDetails.getAuthorName());

        Author updateAuthor = authorRepository.save(author);
        return updateAuthor;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") int authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        authorRepository.delete(author);
        return ResponseEntity.ok().build();
    }
}
