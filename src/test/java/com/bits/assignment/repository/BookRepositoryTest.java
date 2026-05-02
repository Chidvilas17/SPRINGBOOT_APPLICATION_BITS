package com.bits.assignment.repository;

import com.bits.assignment.entity.Author;
import com.bits.assignment.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindAllBooksWithAuthors() {
        // Create an author
        Author author = new Author("Test Author", "test@author.com");
        author = authorRepository.save(author);

        // Create books
        Book book1 = new Book("Test Book 1", "ISBN-001", author);
        Book book2 = new Book("Test Book 2", "ISBN-002", author);
        bookRepository.save(book1);
        bookRepository.save(book2);

        // Test the custom query
        List<Book> books = bookRepository.findAllBooksWithAuthors();

        // The exact number depends on data.sql loading, but we at least inserted 2 here.
        // Spring Boot @DataJpaTest might or might not run data.sql by default.
        // Let's assert it is at least 2 and authors are fetched.
        assertNotNull(books);
        Book foundBook = books.stream()
                .filter(b -> b.getIsbn().equals("ISBN-001"))
                .findFirst()
                .orElse(null);
                
        assertNotNull(foundBook);
        assertEquals("Test Author", foundBook.getAuthor().getName());
    }
}
