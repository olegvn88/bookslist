package com.petprojet.bookslist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class AdviceExceptionController {

    @ExceptionHandler(BooksAlreadyExistsException.class)
    public ErrorResponse<ApiError> handleBooksAlreadyExistsException(BooksAlreadyExistsException e) {
        ApiError error = new ApiError(BAD_REQUEST.value(), BAD_REQUEST.name(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ErrorResponse<>(error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse<ApiError> handleBookNotFoundException(BookNotFoundException e) {
        ApiError error = new ApiError(BAD_REQUEST.value(), BAD_REQUEST.name(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ErrorResponse<>(error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ErrorResponse<ApiError> handleAuthorAlreadyExistsException(AuthorAlreadyExistsException e) {
        ApiError error = new ApiError(BAD_REQUEST.value(), BAD_REQUEST.name(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ErrorResponse<>(error, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ErrorResponse<ApiError> handleAuthorNotFoundException(AuthorNotFoundException e){
        ApiError error = new ApiError(BAD_REQUEST.value(), BAD_REQUEST.name(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ErrorResponse<>(error, new HttpHeaders(), error.getStatus());
    }
}
