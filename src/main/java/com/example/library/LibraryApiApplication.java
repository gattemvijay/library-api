package com.example.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@SpringBootApplication
public class LibraryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApiApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(BookRepository repo) {
        return args -> {
            Book book = new Book();
            book.setTitle("Java 17 in Action");
            book.setAuthor("Venkat Vijay");
            book.setIsbn("978-1234567890");
            book.setBorrowed(false);
            repo.save(book);
        };
    }
    
    @Bean
    public CommandLineRunner demo1(BookRepository repo) {
        return args -> {
            Book book = new Book();
            book.setTitle("Passion for Java");
            book.setAuthor("GVV");
            book.setIsbn("978-1234567960");
            book.setBorrowed(false);
            repo.save(book);
        };
    }
}
