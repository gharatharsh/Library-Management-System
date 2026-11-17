
package com.lms.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService implements LibraryServiceInterface {

	private final LibraryMapper libraryMapper;
	private final LibraryRepository libraryRepository;
	private final AddressRepository addressRepository;
	private final BookRepository bookRepository;

	// CREATE
	@Override
	public ResponseEntity<Void> saveLibrary(LibraryDto libraryDto, Integer addressId) {

		// Fetch Address
		Optional<Address> optionalAddress = addressRepository.findById(addressId);

		if (optionalAddress.isEmpty()) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Address Not Found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		Address address = optionalAddress.get();

		Library library = libraryMapper.convertLibraryDtoToEntity(libraryDto);

		library.setAddress(address);

		libraryRepository.save(library);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Library Saved Successfully");

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}

	// UPDATE
	@Override
	public ResponseEntity<Void> updateLibrary(LibraryDto libraryDto, Integer libraryId, Integer addressId) {

		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);

		if (optionalLibrary.isEmpty()) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Library Not Found");
			// return "Library Not Found";

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		Optional<Address> optionalAddress = addressRepository.findById(addressId);

		if (optionalAddress.isEmpty()) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Address Not Found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		Library library = optionalLibrary.get();

		Library updatedLibrary = libraryMapper.convertLibraryDtoToEntity(libraryDto);

		library.setAddress(optionalAddress.get());

		libraryRepository.save(library);

		// return "Library Updated Successfully";

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Library Updated Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	}

	// DELETE
	@Override
	public ResponseEntity<Void> deleteLibrary(Integer libraryId) {

		if (!libraryRepository.existsById(libraryId)) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Library Not Found");

			// return "Library Not Found";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		libraryRepository.deleteById(libraryId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Library Deleted Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();

		// return "Library Deleted Successfully";
	}

	// READ BY ID
	@Override
	public ResponseEntity<Library> findLibraryById(Integer libraryId) {

		Optional<Library> optional = libraryRepository.findById(libraryId);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			Library library = optional.get();

			headers.add("message", "Library Found Successfully for this ID");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(library);

			// return optional.get().toString();
		} else {

			// headers.add("message", "Library Not Found for this ID");
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();

			throw new LibraryNotFoundException("Library not found for this id" + libraryId);
		}

		// return "Library Not Found";
	}

	// READ ALL
	@Override
	public ResponseEntity<List<Library>> findAllLibrary() {

		List<Library> libraryList = libraryRepository.findAll();

		// return libraryList.toString();
		return ResponseEntity.status(HttpStatus.OK).body(libraryList);
	}

	@Override
	public ResponseEntity<Library> findByLibraryName(String libraryName) {

		Optional<Library> optional = libraryRepository.findByLibraryName(libraryName);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "Library Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new LibraryNotFoundException("Library not found with name " + libraryName);
	}

	@Override
	public ResponseEntity<Library> findByPhoneNumber(Long phoneNumber) {

		Optional<Library> optional = libraryRepository.findByPhoneNumber(phoneNumber);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "Library Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new LibraryNotFoundException("Library not found with this  phone number " + phoneNumber);
	}

	@Override
	public ResponseEntity<Void> addBookToLibrary(int libraryId, int bookId) {

		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		Optional<Book> optionalBook = bookRepository.findById(bookId);

		if (optionalLibrary.isEmpty()) {
			throw new LibraryNotFoundException("Library not found");
		}

		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found");
		}

		Library library = optionalLibrary.get();
		Book book = optionalBook.get();

		List<Book> books = library.getBooks();

		if (books == null) {
			books = new ArrayList<>();
		}

		books.add(book);

		library.setBooks(books);

		libraryRepository.save(library);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Book Added to Library Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	}

}