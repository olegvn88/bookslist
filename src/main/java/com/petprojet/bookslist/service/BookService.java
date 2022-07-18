package com.petprojet.bookslist.service;

import com.petprojet.bookslist.entity.BookEntity;
import com.petprojet.bookslist.exception.BookNotFoundException;
import com.petprojet.bookslist.exception.BooksAlreadyExistsException;
import com.petprojet.bookslist.repository.BookRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public static final String THE_BOOK_NAME_ALREADY_EXISTS = "The book name already exists";
    @Autowired
    private BookRepo bookRepo;

    public BookEntity addBook(BookEntity bookEntity) throws BooksAlreadyExistsException {
        Optional<BookEntity> book = bookRepo.findByName(bookEntity.getName());
        if (book.isPresent()) {
            throw new BooksAlreadyExistsException(THE_BOOK_NAME_ALREADY_EXISTS);
        } return bookRepo.save(bookEntity);
    }

    public List<BookEntity> getBooks() {
        return bookRepo.findAll();
    }

    public BookEntity getBookById(Long id) throws BookNotFoundException {
        Optional<BookEntity> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new BookNotFoundException("Book not found");
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<BookEntity> book = bookRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found");
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
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setPrice(book.getPrice());


        bookRepo.save(updatedBook);
        return updatedBook;
    }
}
