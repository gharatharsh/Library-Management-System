package com.lms.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.util.ApiError;

@ControllerAdvice
public class LibraryManagementExceptionHandler {

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException exception) {
		// message ,Status ,details, date & time

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());

		List<String> details = new ArrayList<>();

		details.add("Address not Found");
		error.setDetails(details);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Book Not Found Exception
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());

		List<String> details = new ArrayList<>();
		details.add("Book not Found");

		error.setDetails(details);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Library Not Found Exception
	@ExceptionHandler(LibraryNotFoundException.class)
	public ResponseEntity<Object> handleLibraryNotFoundException(LibraryNotFoundException exception) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());

		List<String> details = new ArrayList<>();
		details.add("Library not Found");

		error.setDetails(details);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	// User Not Found Exception
	
	public ResponseEntity<Object> handerUserNotFoundException(UserNotFoundException exception){
		
		ApiError error = new ApiError();

		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());

		List<String> details = new ArrayList<>();
		details.add("Library not Found");

		error.setDetails(details);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
