
package com.lms.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.BorrowBookNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {

	private final LibraryMapper libraryMapper;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<Void> saveBook(BookDto bookDto) {

		Book book = libraryMapper.convertBookDtoToEntity(bookDto);

		bookRepository.save(book);

		// return "Book Data Saved Successfully";

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", " Book Save Successfully");

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}

	@Override
	public ResponseEntity<Void> updateBook(BookDto bookDto) {

		if (bookDto.getBookId() == null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Book ID is required");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
		}

		Optional<Book> optional = bookRepository.findById(bookDto.getBookId());

		if (optional.isPresent()) {

			Book book = libraryMapper.convertBookDtoToEntity(bookDto);

			bookRepository.save(book);

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Book Updated Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Not Found");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
	}

	@Override
	public ResponseEntity<Void> deleteBook(Integer bookId) {

		if (!bookRepository.existsById(bookId)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Book Not Found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		bookRepository.deleteById(bookId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Deleted Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();

		// return "Book Deleted Successfully";
	}

	@Override
	public ResponseEntity<Book> findBookById(Integer bookId) {

		Optional<Book> optional = bookRepository.findById(bookId);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			Book book = optional.get();

			// return "Book Not Found";
			headers.add("message", "Book found Successfully for this id");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(book);

		} else {

			// headers.add("message", "Book not found for this id");
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();

			// throw
			// and then handle it
			throw new BookNotFoundException("Book not found for this id" + bookId);
		}
	}

	@Override
	public ResponseEntity<List<Book>> findAllBook() {

		List<Book> bookList = bookRepository.findAll();

		// return bookList.toString();

		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	@Override
	public ResponseEntity<Book> findByTitle(String title) {

		Optional<Book> optional = bookRepository.findByTitle(title);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "Book Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new BookNotFoundException("Book not found with title " + title);
	}

	@Override
	public ResponseEntity<List<Book>> findByAuthor(String author) {

		List<Book> bookList = bookRepository.findByAuthor(author);

		if (bookList.isEmpty()) {
			throw new AddressNotFoundException("Book not found for author " + author);
		}

		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	@Override
	public ResponseEntity<List<Book>> findByCategory(String category) {

		List<Book> bookList = bookRepository.findByCategory(category);

		if (bookList.isEmpty()) {
			throw new BookNotFoundException("Book not found for category " + category);
		}

		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	@Override
	public ResponseEntity<List<Book>> findByCopiesGreaterThan(Integer copies) {

		List<Book> bookList = bookRepository.findByCopiesGreaterThan(copies);

		if (bookList.isEmpty()) {
			throw new BookNotFoundException("No books found with copies greater than " + copies);
		}

		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	// For Book Borrow

	@Override
	public ResponseEntity<BookDto> borrowBook(int bookId, int userId) {

		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isPresent() && optionalBook.isPresent() && optionalBook.get().getCopies() > 0) {

			Book book = optionalBook.get();
			User user = optionalUser.get();

			List<User> users = book.getUser();

			if (users == null) {
				users = new ArrayList<>();
			}

			users.add(user);

			book.setBorrowTime(LocalDateTime.now());
			book.setCopies(book.getCopies() - 1);
			book.setUser(users);

			bookRepository.save(book);

			BookDto bookDto = libraryMapper.convertBookToDto(book);

			return ResponseEntity.status(HttpStatus.OK).body(bookDto);
		}

		throw new BorrowBookNotFoundException("No books found");
	}

	@Override
	public ResponseEntity<BookDto> returnBook(int bookId, int userId) {

		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalBook.isPresent() && optionalUser.isPresent()) {

			Book book = optionalBook.get();
			User user = optionalUser.get();

			List<User> users = book.getUser();

			if (users != null && users.contains(user)) {

				users.remove(user);

				book.setReturnTime(LocalDateTime.now());
				book.setCopies(book.getCopies() + 1);
				book.setUser(users);

				bookRepository.save(book);

				BookDto bookDto = libraryMapper.convertBookToDto(book);

				return ResponseEntity.status(HttpStatus.OK).body(bookDto);
			}
		}

		throw new BorrowBookNotFoundException("Book was not borrowed by this user");
	}
}
