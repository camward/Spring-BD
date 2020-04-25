package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books WHERE author_name = ?1", nativeQuery = true)
    Book findBooksByAuthor(String author_name);

    @Query(value = "SELECT * FROM books WHERE author_name = :nameParam", nativeQuery = true)
    Book findBooksByAuthorName(@Param("nameParam") String author_name);
}
