package com.petprojet.bookslist.exception;

public class BooksAlreadyExistsException extends RuntimeException {

    public BooksAlreadyExistsException(String message) {
        super(message);
    }
}
