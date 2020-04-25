package com.example.demo.controllers;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return (Book) bookRepository.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) throws Throwable {
        return (Book) bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody Book bookDetails) throws Throwable {

        Book book = (Book) bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBook_name(bookDetails.getBook_name());
        book.setAuthor_name(bookDetails.getAuthor_name());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = (Book) bookRepository.save(book);
        return updatedBook;
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable(value = "id") Long bookId) throws Throwable {
        Book book = (Book) bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }
}
