package com.example.library.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.exception.BookNotBorrowedException;

class LibraryServiceTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private BorrowerRepository borrowerRepo;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Mock Book");

        when(bookRepo.save(book)).thenReturn(book);

        Book result = libraryService.addBook(book);

        assertEquals("Mock Book", result.getTitle());
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    void testRegisterBorrower() {
        Borrower borrower = new Borrower();
        borrower.setName("Gattem Raghuveer");

        when(borrowerRepo.save(borrower)).thenReturn(borrower);

        Borrower result = libraryService.registerBorrower(borrower);

        assertEquals("Gattem Raghuveer", result.getName());
        verify(borrowerRepo, times(1)).save(borrower);
    }

    @Test
    void testListAllBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookRepo.findAll()).thenReturn(books);

        List<Book> result = libraryService.listAllBooks();

        assertEquals(2, result.size());
        verify(bookRepo, times(1)).findAll();
    }

    @Test
    void testBorrowBook_success() {
        Book book = new Book();
        book.setBorrowed(false);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

        libraryService.borrowBook(1L, 101L);

        assertTrue(book.isBorrowed());
        verify(bookRepo).save(book);
    }

    @Test
    void testBorrowBook_alreadyBorrowed() {
        Book book = new Book();
        book.setBorrowed(true);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                libraryService.borrowBook(1L, 101L));

        assertEquals("Already borrowed", ex.getMessage());
    }

    @Test
    void testReturnBook_success() {
        Book book = new Book();
        book.setBorrowed(true);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

        libraryService.returnBook(1L);

        assertFalse(book.isBorrowed());
        verify(bookRepo).save(book);
    }

    @Test
    void testReturnBook_notBorrowed() {
        Book book = new Book();
        book.setBorrowed(false);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

        BookNotBorrowedException ex = assertThrows(BookNotBorrowedException.class, () ->
                libraryService.returnBook(1L));

        assertEquals("This book is not borrowed yet", ex.getMessage());
    }

    @Test
    void testReturnBook_notFound() {
        when(bookRepo.findById(1L)).thenReturn(Optional.empty());

        BookNotBorrowedException ex = assertThrows(BookNotBorrowedException.class, () ->
                libraryService.returnBook(1L));

        assertEquals("Book with ID 1 not found", ex.getMessage());
    }
}

