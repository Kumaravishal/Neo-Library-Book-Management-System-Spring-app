package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
}