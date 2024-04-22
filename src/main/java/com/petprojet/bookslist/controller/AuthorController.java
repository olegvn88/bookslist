package com.petprojet.bookslist.controller;

import com.petprojet.bookslist.entity.AuthorEntity;
import com.petprojet.bookslist.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> addAuthor(@RequestBody AuthorEntity author) {
        AuthorEntity authorEntity = authorService.addAuthor(author);
        return ResponseEntity.ok(authorEntity);
    }

    @GetMapping
    public ResponseEntity<List<AuthorEntity>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }


    @GetMapping(value = "/{authorId}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long authorId) {
        AuthorEntity authorById = authorService.getAuthorById(authorId);
        return ResponseEntity.ok(authorById);
    }

    @PutMapping(value = "/{authorId}")
    public ResponseEntity<AuthorEntity> updateAuthor(@RequestBody AuthorEntity author, @PathVariable Long authorId) {
        return ResponseEntity.created(URI.create("")).body(authorService.updateAuthor(author, authorId));
    }

    @DeleteMapping(value = "/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
