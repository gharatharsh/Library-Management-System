
package com.lms.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Dto.LibraryDto;
import com.lms.entity.Library;

public interface LibraryServiceInterface {

	ResponseEntity<Void> saveLibrary(LibraryDto libraryDto, Integer addressId);

	ResponseEntity<Void> updateLibrary(LibraryDto libraryDto, Integer libraryId, Integer addressId);

	ResponseEntity<Void> deleteLibrary(Integer libraryId);

	ResponseEntity<Library> findLibraryById(Integer libraryId);

	ResponseEntity<List<Library>> findAllLibrary();

	ResponseEntity<Library> findByLibraryName(String libraryName);

	ResponseEntity<Library> findByPhoneNumber(Long phoneNumber);
	
	ResponseEntity<Void> addBookToLibrary(int libraryId, int bookId);
}