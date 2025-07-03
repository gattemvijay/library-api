package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.BookNotBorrowedException;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;

@Service
public class LibraryService {

	private final BookRepository bookRepo;
	private final BorrowerRepository borrowerRepo;

	public LibraryService(BookRepository bookRepo, BorrowerRepository borrowerRepo) {
		this.bookRepo = bookRepo;
		this.borrowerRepo = borrowerRepo;
	}

	public Book addBook(Book book) {
		return bookRepo.save(book);
	}

	public Borrower registerBorrower(Borrower borrower) {
		return borrowerRepo.save(borrower);
	}

	public List<Book> listAllBooks() {
		return bookRepo.findAll();
	}

	public void borrowBook(Long bookId, Long borrowerId) {
		Book book = bookRepo.findById(bookId).orElseThrow();
		if (book.isBorrowed())
			throw new IllegalStateException("Already borrowed");

		book.setBorrowed(true);
		bookRepo.save(book);
	}

	public void returnBook(Long bookId) {
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new BookNotBorrowedException("Book with ID " + bookId + " not found"));

		if (!book.isBorrowed()) {
			throw new BookNotBorrowedException("This book is not borrowed yet");
		}
		book.setBorrowed(false);
		bookRepo.save(book);
	}
}
