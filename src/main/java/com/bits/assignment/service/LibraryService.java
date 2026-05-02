package com.bits.assignment.service;

import com.bits.assignment.entity.Author;
import com.bits.assignment.entity.Book;
import com.bits.assignment.repository.AuthorRepository;
import com.bits.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Book> getAllBooks() {
        // Using the custom query with inner join
        return bookRepository.findAllBooksWithAuthors();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setAuthor(bookDetails.getAuthor());
        bookRepository.save(book);
    }
}
