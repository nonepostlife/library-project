package ru.springproject.libraryproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.springproject.libraryproject.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    // * Year
    List<Book> findAllByBookYearWritingIs(int year, Pageable pageable);

    // * Name
    @Query("Select distinct b from Book b left join b.authors a where b.bookName like CONCAT('%',?1,'%') or a.authorName like CONCAT('%',?2,'%')")
    List<Book> findAllByBookNameOrAuthorNameContaining(String text, String textAgain, Pageable pageable);

    // * Count
    @Query("Select b from Book b left join b.authors a group by b having count(a) = ?1")
    List<Book> findAllByAuthorsCountIs(long count, Pageable pageable);

    // * Year + Name
    @Query("Select distinct b from Book b left join b.authors a where b.bookYearWriting = ?1 and (b.bookName like CONCAT('%',?2,'%') or a.authorName like CONCAT('%',?3,'%'))")
    List<Book> findAllByBookYearWritingIsAndBookNameOrAuthorNameContaining(int year, String text, String textAgain, Pageable pageable);

    // * Year + Count
    @Query("Select b from Book b left join b.authors a where b.bookYearWriting = ?1 group by b having count(a) = ?2")
    List<Book> findAllByBookYearWritingIsAndAuthorsCountIs(int year, long count, Pageable pageable);

    // * Name + Count
    @Query("Select distinct b from Book b left join b.authors a where b.bookName like CONCAT('%',?1,'%') or a.authorName like CONCAT('%',?2,'%') group by b having count(a) = ?3")
    List<Book> findByBookNameOrAuthorNameContainingAndAuthorCountIs(String text, String textAgain, long count, Pageable pageable);

    // Year + Name + Count
    @Query("Select distinct b from Book b left join b.authors a where b.bookYearWriting = ?1 and (b.bookName like CONCAT('%',?2,'%') or a.authorName like CONCAT('%',?3,'%')) group by b having count(a) = ?4")
    List<Book> findByBookYearWritingIsAndBookNameOrAuthorNameContainingAndAuthorCountIs(int year, String text, String textAgain, long count, Pageable pageable);

}
