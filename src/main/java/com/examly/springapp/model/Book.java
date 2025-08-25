package com.examly.springapp.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookTitle;
    private String authorName;
    private String genre;
    private String isbn;
    private String publisher;
    private String yearPublished;

    public Book() {}

    public Book(String bookTitle, String authorName, String genre,
                String isbn, String publisher, String yearPublished) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.genre = genre;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getYearPublished() { return yearPublished; }
    public void setYearPublished(String yearPublished) { this.yearPublished = yearPublished; }
}
