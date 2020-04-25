package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Optional getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId);
    }
}
