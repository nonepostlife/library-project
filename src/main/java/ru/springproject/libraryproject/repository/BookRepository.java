package ru.springproject.libraryproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.springproject.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

//    @Query( value = "select * from book order by idbook",
//            countQuery = "select count(*) from book",
//            nativeQuery = true)

    List<Book> findAllByBookYearWritingGreaterThan(int year, Pageable pageable);

    //List<Book> findByBookYearWritingEquals(int year);


}
