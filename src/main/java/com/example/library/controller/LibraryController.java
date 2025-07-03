package com.example.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.service.LibraryService;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(service.addBook(book));
    }

    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(service.registerBorrower(borrower));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(service.listAllBooks());
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<Void> borrowBook(@PathVariable("bookId")  Long bookId, @RequestParam("borrowerId") Long borrowerId) {
        service.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<Void> returnBook(@PathVariable("bookId") Long bookId) {
        service.returnBook(bookId);
        return ResponseEntity.ok().build();
    }
}

