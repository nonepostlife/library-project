package ru.springproject.libraryproject.repository;

import ru.springproject.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    //List<Book> findByBookNameContainingOrAuthorContaining(String text, String textAgain);
    //List<Book> findByYearWriting(int year);
}
