package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.AuthorEntity;
import com.petprojet.bookslist.exception.AuthorAlreadyExistsException;
import com.petprojet.bookslist.exception.AuthorNotFoundException;
import com.petprojet.bookslist.repository.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    public static final String AUTHOR_NOT_FOUND = "Author not found";
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public AuthorEntity addAuthor(AuthorEntity author) {
        Optional<AuthorEntity> byName = authorRepo.findByName(author.getName());
        if (byName.isPresent()) {
            throw new AuthorAlreadyExistsException("Author with such name already exists");
        }
        return authorRepo.save(author);
    }

    public List<AuthorEntity> getAuthors() {
        return authorRepo.findAll();
    }

    public AuthorEntity getAuthorById(Long id) {
        Optional<AuthorEntity> author = authorRepo.findById(id);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException(AUTHOR_NOT_FOUND);
        }
        return author.get();
    }

    public AuthorEntity updateAuthor(AuthorEntity author, Long authorId) {
        Optional<AuthorEntity> authorById = authorRepo.findById(authorId);
        if (authorById.isEmpty()) {
            throw new AuthorNotFoundException(AUTHOR_NOT_FOUND);
        }
//        AuthorEntity authorEntity = new AuthorEntity();
//        authorEntity.setId(authorId);
//        authorEntity.setName(author.getName());
//        authorEntity.setGenre(author.getGenre());
        AuthorEntity authorEntity = AuthorEntity.builder()
                                                .id(authorId)
                                                .name(author.getName())
                                                .genre(author.getGenre())
                                                .build();
        return authorRepo.save(authorEntity);
    }

    public void deleteAuthor(Long authorId) {
        Optional<AuthorEntity> author = authorRepo.findById(authorId);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException(AUTHOR_NOT_FOUND);
        }
        authorRepo.deleteById(authorId);
    }
}
