package com.petprojet.bookslist.controller;

import com.petprojet.bookslist.entity.BookEntity;
import com.petprojet.bookslist.exception.BookNotFoundException;
import com.petprojet.bookslist.exception.BooksAlreadyExistsException;
import com.petprojet.bookslist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookEntity book) {
        try {
            return ResponseEntity.ok(bookService.addBook(book));
        } catch (BooksAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneBook(@PathVariable Long id) {
        try {
            BookEntity bookEntity = bookService.getBookById(id);
            return ResponseEntity.ok(bookEntity);
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book is deleted successfully");
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editBook(@RequestBody BookEntity book, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.updateBook(book, id));
        } catch (BookNotFoundException | BooksAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
