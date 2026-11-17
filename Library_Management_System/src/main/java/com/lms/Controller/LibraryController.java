
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

import com.lms.Dto.LibraryDto;
import com.lms.Service.LibraryServiceInterface;
import com.lms.entity.Library;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LibraryController {

	private final LibraryServiceInterface libraryServiceInterface;

	// CREATE LIBRARY
	@Operation(operationId = "CreateLibrary", summary = "Create new library", description = "Creates and saves a new library with its associated address.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Library created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid library details or address ID"),
			@ApiResponse(responseCode = "404", description = "Address not found") })
	@PostMapping("/saveLibrary")
	public ResponseEntity<Void> saveLibrary(@Valid @RequestBody LibraryDto libraryDto,
			@RequestParam("addressId") @Positive Integer addressId) {

		return libraryServiceInterface.saveLibrary(libraryDto, addressId);
	}

	// UPDATE LIBRARY
	@Operation(operationId = "UpdateLibrary", summary = "Update library", description = "Updates an existing library and its associated address.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Library updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid library ID, address ID or details"),
			@ApiResponse(responseCode = "404", description = "Library or address not found") })
	@PutMapping("/updateLibrary")
	public ResponseEntity<Void> updateLibrary(@Valid @RequestBody LibraryDto libraryDto,
			@RequestParam("libraryId") @Positive Integer libraryId,
			@RequestParam("addressId") @Positive Integer addressId) {

		return libraryServiceInterface.updateLibrary(libraryDto, libraryId, addressId);
	}

	// DELETE LIBRARY
	@Operation(operationId = "DeleteLibrary", summary = "Delete library", description = "Deletes an existing library using its library ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Library deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid library ID"),
			@ApiResponse(responseCode = "404", description = "Library not found") })
	@DeleteMapping("/deleteLibrary")
	public ResponseEntity<Void> deleteLibrary(@Valid @RequestParam("libraryId") @Positive Integer libraryId) {

		return libraryServiceInterface.deleteLibrary(libraryId);
	}

	// FIND LIBRARY BY ID
	@Operation(operationId = "FindLibraryById", summary = "Find library by ID", description = "Retrieves a library using its unique library ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Library found successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid library ID"),
			@ApiResponse(responseCode = "404", description = "Library not found") })
	@GetMapping("/findLibraryById")
	public ResponseEntity<Library> findLibraryById(@Valid @RequestParam("libraryId") @Positive Integer libraryId) {

		return libraryServiceInterface.findLibraryById(libraryId);
	}

	// FIND ALL LIBRARIES
	@Operation(operationId = "FindAllLibrary", summary = "Get all libraries", description = "Retrieves the list of all registered libraries.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Libraries retrieved successfully") })
	@GetMapping("/findAllLibrary")
	public ResponseEntity<List<Library>> findAllLibrary() {

		return libraryServiceInterface.findAllLibrary();
	}

	// FIND BY LIBRARY NAME
	@Operation(operationId = "FindLibraryByName", summary = "Find library by name", description = "Retrieves a library using its library name.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Library found successfully"),
			@ApiResponse(responseCode = "404", description = "Library not found") })
	@GetMapping("/findByLibraryName")
	public ResponseEntity<Library> findByLibraryName(@RequestParam("libraryName") String libraryName) {

		return libraryServiceInterface.findByLibraryName(libraryName);
	}

	// FIND BY PHONE NUMBER
	@Operation(operationId = "FindLibraryByPhoneNumber", summary = "Find library by phone number", description = "Retrieves a library using its registered phone number.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Library found successfully"),
			@ApiResponse(responseCode = "404", description = "Library not found") })
	@GetMapping("/findByPhoneNumber")
	public ResponseEntity<Library> findByPhoneNumber(@Valid @RequestParam("phoneNumber") Long phoneNumber) {

		return libraryServiceInterface.findByPhoneNumber(phoneNumber);
	}
}
