package com.examly.springapp.exception;

public class InvalidIsbnException extends RuntimeException {
    public InvalidIsbnException(String message) {
        super(message);
    }
}