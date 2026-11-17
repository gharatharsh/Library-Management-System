package com.lms.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Dto.BookDto;
import com.lms.entity.Book;

public interface BookServiceInterface {

    ResponseEntity<Void> saveBook(BookDto bookDto);

    ResponseEntity<Void> updateBook(BookDto bookDto);

    ResponseEntity<Void> deleteBook(Integer bookId);

    ResponseEntity<Book> findBookById(Integer bookId);

    ResponseEntity<List<Book>> findAllBook();

    ResponseEntity<Book> findByTitle(String title);

    ResponseEntity<List<Book>> findByAuthor(String author);

    ResponseEntity<List<Book>> findByCategory(String category);

    ResponseEntity<List<Book>> findByCopiesGreaterThan(Integer copies);
    
    ResponseEntity<BookDto> borrowBook(int bookId, int userId);
    
    ResponseEntity<BookDto> returnBook(int bookId, int userId);
}