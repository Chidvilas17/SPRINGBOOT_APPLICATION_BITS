package com.bits.assignment.service;

import com.bits.assignment.entity.Author;
import com.bits.assignment.entity.Book;
import com.bits.assignment.repository.AuthorRepository;
import com.bits.assignment.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        Author a1 = new Author("John", "john@email.com");
        Author a2 = new Author("Jane", "jane@email.com");
        when(authorRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Author> authors = libraryService.getAllAuthors();

        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void getAuthorById_Found() {
        Author a = new Author("John", "john@email.com");
        a.setId(1L);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(a));

        Author result = libraryService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void getAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> libraryService.getAuthorById(1L));
    }

    @Test
    void saveAuthor() {
        Author a = new Author("John", "john@email.com");
        when(authorRepository.save(any(Author.class))).thenReturn(a);

        Author result = libraryService.saveAuthor(a);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(authorRepository, times(1)).save(a);
    }

    @Test
    void getAllBooks() {
        Book b1 = new Book();
        Book b2 = new Book();
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(b1, b2));

        List<Book> books = libraryService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }
}
