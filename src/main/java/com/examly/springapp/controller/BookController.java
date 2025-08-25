package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.Book;
import com.examly.springapp.service.BookService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error adding book: " + e.getMessage());
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching books: " + e.getMessage());
        }
    }
}