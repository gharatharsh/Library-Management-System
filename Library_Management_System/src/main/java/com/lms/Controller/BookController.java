
package com.lms.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.Dto.BookDto;
import com.lms.Service.BookServiceInterface;
import com.lms.entity.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookServiceInterface bookServiceInterface;

	// CREATE BOOK
	@Operation(operationId = "CreateBook", summary = "Create new book", description = "Adds a new book to the library.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Book created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid book details") })
	@PostMapping("/saveBook")
	public ResponseEntity<Void> saveBook(@Valid @RequestBody BookDto bookDto) {

		return bookServiceInterface.saveBook(bookDto);
	}

	// UPDATE BOOK
	@Operation(operationId = "UpdateBook", summary = "Update book", description = "Updates the details of an existing book.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid book details or book ID"),
			@ApiResponse(responseCode = "404", description = "Book not found") })
	@PutMapping("/updateBook")
	public ResponseEntity<Void> updateBook(@Valid @RequestBody BookDto bookDto) {

		return bookServiceInterface.updateBook(bookDto);
	}

	// DELETE BOOK
	@Operation(operationId = "DeleteBook", summary = "Delete book", description = "Deletes an existing book using its book ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid book ID"),
			@ApiResponse(responseCode = "404", description = "Book not found") })
	@DeleteMapping("/deleteBook")
	public ResponseEntity<Void> deleteBook(@Valid @RequestParam("bookId") @Positive Integer bookId) {

		return bookServiceInterface.deleteBook(bookId);
	}

	// FIND BOOK BY ID
	@Operation(operationId = "FindBookById", summary = "Find book by ID", description = "Retrieves a book using its unique book ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book found successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid book ID"),
			@ApiResponse(responseCode = "404", description = "Book not found") })
	@GetMapping("/findBookById")
	public ResponseEntity<Book> findBookById(@Valid @RequestParam("bookId") @Positive Integer bookId) {

		return bookServiceInterface.findBookById(bookId);
	}

	// FIND ALL BOOKS
	@Operation(operationId = "FindAllBook", summary = "Get all books", description = "Retrieves all books available in the library.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books retrieved successfully") })
	@GetMapping("/findAllBook")
	public ResponseEntity<List<Book>> findAllBook() {

		return bookServiceInterface.findAllBook();
	}

	// FIND BY TITLE
	@Operation(operationId = "FindBookByTitle", summary = "Find book by title", description = "Retrieves a book using its title.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book found successfully"),
			@ApiResponse(responseCode = "404", description = "Book not found") })
	@GetMapping("/findByTitle")
	public ResponseEntity<Book> findByTitle(@RequestParam("title") String title) {

		return bookServiceInterface.findByTitle(title);
	}

	// FIND BY AUTHOR
	@Operation(operationId = "FindBookByAuthor", summary = "Find books by author", description = "Retrieves all books written by a particular author.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found successfully"),
			@ApiResponse(responseCode = "404", description = "No books found for this author") })
	@GetMapping("/findByAuthor")
	public ResponseEntity<List<Book>> findByAuthor(@RequestParam("author") String author) {

		return bookServiceInterface.findByAuthor(author);
	}

	// FIND BY CATEGORY
	@Operation(operationId = "FindBookByCategory", summary = "Find books by category", description = "Retrieves all books belonging to a particular category.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found successfully"),
			@ApiResponse(responseCode = "404", description = "No books found in this category") })
	@GetMapping("/findByCategory")
	public ResponseEntity<List<Book>> findByCategory(@RequestParam("category") String category) {

		return bookServiceInterface.findByCategory(category);
	}

	// FIND BY COPIES GREATER THAN
	@Operation(operationId = "FindBookByCopiesGreaterThan", summary = "Find books by available copies", description = "Retrieves books having copies greater than the specified number.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid copies value"),
			@ApiResponse(responseCode = "404", description = "No books found") })
	@GetMapping("/findByCopiesGreaterThan")
	public ResponseEntity<List<Book>> findByCopiesGreaterThan(@RequestParam("copies") @Positive Integer copies) {

		return bookServiceInterface.findByCopiesGreaterThan(copies);
	}

	// RETURN BOOK
	@Operation(operationId = "ReturnBook", summary = "Return borrowed book", description = "Returns a borrowed book using book ID and user ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book returned successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid book ID or user ID"),
			@ApiResponse(responseCode = "404", description = "Book or user not found") })
	@PutMapping("/returnBook")
	public ResponseEntity<BookDto> returnBook(@RequestParam("bookId") @Positive int bookId,
			@RequestParam("userId") @Positive int userId) {

		return bookServiceInterface.returnBook(bookId, userId);
	}
}
