package com.petprojet.bookslist.exception;

public class BooksAlreadyExistsException extends Exception{

    public BooksAlreadyExistsException(String message) {
        super(message);
    }

    public BooksAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
