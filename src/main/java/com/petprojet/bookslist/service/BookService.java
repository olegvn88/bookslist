package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.AuthorEntity;
import com.petprojet.bookslist.entity.BookEntity;
import com.petprojet.bookslist.exception.AuthorNotFoundException;
import com.petprojet.bookslist.exception.BookNotFoundException;
import com.petprojet.bookslist.exception.BooksAlreadyExistsException;
import com.petprojet.bookslist.repository.AuthorRepo;
import com.petprojet.bookslist.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    public static final String THE_BOOK_NAME_ALREADY_EXISTS = "The book name already exists";
    public static final String BOOK_NOT_FOUND = "Book not found";
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookService(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public BookEntity addBook(BookEntity bookEntity) {
        Optional<BookEntity> book = bookRepo.findByName(bookEntity.getName());
        if (book.isPresent()) {
            throw new BooksAlreadyExistsException(THE_BOOK_NAME_ALREADY_EXISTS);
        }
        return bookRepo.save(bookEntity);
    }

    public List<BookEntity> getBooks() {
        return bookRepo.findAll();
    }

    public BookEntity getBookById(Long id) {
        Optional<BookEntity> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new BookNotFoundException(BOOK_NOT_FOUND);
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<BookEntity> book = bookRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        bookRepo.deleteById(id);
    }

    public BookEntity updateBook(BookEntity book, Long id) throws BookNotFoundException, BooksAlreadyExistsException {
        Optional<BookEntity> bookEntity = bookRepo.findById(id);
        if (bookEntity.isEmpty()) {
            throw new BookNotFoundException("Book doesn't exists");
        }
        if (bookEntity.get().getName().equals(book.getName())) {
            throw new BooksAlreadyExistsException(THE_BOOK_NAME_ALREADY_EXISTS);
        }
        BookEntity updatedBook = bookEntity.get();

        updatedBook.setName(book.getName());
        updatedBook.setAssignedAuthors(book.getAssignedAuthors());
        updatedBook.setPrice(book.getPrice());


        bookRepo.save(updatedBook);
        return updatedBook;
    }

    public BookEntity assignAuthorToBook(Long bookId, Long authorId) {
        Optional<BookEntity> book = bookRepo.findById(bookId);
        if (book.isEmpty()) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        Optional<AuthorEntity> author = authorRepo.findById(authorId);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("Author not found");
        }

//        Set<AuthorEntity> authors = book.get().getAssignedAuthors();
//        authors.add(author.get());
//        book.get().setAssignedAuthors(authors);
        book.get().getAssignedAuthors().add(author.get());
        return bookRepo.save(book.get());
    }
}
