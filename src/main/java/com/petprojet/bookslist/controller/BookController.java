package com.petprojet.bookslist.controller;

import com.petprojet.bookslist.entity.BookEntity;
import com.petprojet.bookslist.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        return ResponseEntity.created(URI.create("")).body(bookService.addBook(book));
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getOneBook(@PathVariable Long id) {
        BookEntity bookEntity = bookService.getBookById(id);
        return ResponseEntity.ok(bookEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editBook(@RequestBody BookEntity book, @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @PutMapping("/{bookId}/assignAuthor/{authorId}")
    public ResponseEntity<BookEntity> assignAuthorToBook(@PathVariable Long bookId,
                                                         @PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.assignAuthorToBook(bookId, authorId));
    }
}
