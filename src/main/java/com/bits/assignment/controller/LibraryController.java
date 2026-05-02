package com.bits.assignment.controller;

import com.bits.assignment.entity.Author;
import com.bits.assignment.entity.Book;
import com.bits.assignment.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/authors")
    public String showAuthors(Model model) {
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/authors/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "author-form";
        }
        try {
            libraryService.saveAuthor(author);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Could not save author. Ensure the email is unique.");
            return "author-form";
        }
        return "redirect:/authors";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", libraryService.getAllBooks());
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "book-form";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "book-form";
        }
        try {
            libraryService.saveBook(book);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Could not save book. Ensure the ISBN is unique.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "book-form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the book.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "book-form";
        }
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
        Book book = libraryService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "book-form";
    }

    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "book-form";
        }
        try {
            libraryService.updateBook(id, book);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Could not update book. Ensure the ISBN is unique.");
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "book-form";
        }
        return "redirect:/books";
    }
}
