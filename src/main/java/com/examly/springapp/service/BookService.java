package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.repository.BookRepo;
import com.examly.springapp.model.Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
}