package ru.springproject.libraryproject.repository;

import ru.springproject.libraryproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    //List<Book> findByBookNameContainingOrAuthorContaining(String text, String textAgain);
    //List<Book> findByYearWriting(int year);
}