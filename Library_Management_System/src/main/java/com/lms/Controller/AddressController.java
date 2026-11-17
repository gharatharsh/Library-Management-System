
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

import com.lms.Dto.AddressDto;
import com.lms.Service.AddressServiceInterface;
import com.lms.entity.Address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AddressController {

	private final AddressServiceInterface addressserviceInterface;

	// CREATE ADDRESS
	@Operation(operationId = "CreateAddress", summary = "Create new address", description = "Creates and saves a new address.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Address created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid address details") })
	@PostMapping("/saveAddress")
	public ResponseEntity<Void> saveAddress(@Valid @RequestBody AddressDto addressDto) {

		return addressserviceInterface.saveAddress(addressDto);
	}

	// UPDATE ADDRESS
	@Operation(operationId = "UpdateAddress", summary = "Update address", description = "Updates an existing address using its address ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Address updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid address ID or details"),
			@ApiResponse(responseCode = "404", description = "Address not found") })
	@PutMapping("/updateAddress")
	public ResponseEntity<Void> updateAddress(@Valid @RequestBody AddressDto addressDto) {

		return addressserviceInterface.updateAddress(addressDto);
	}

	// DELETE ADDRESS
	@Operation(operationId = "DeleteAddress", summary = "Delete address", description = "Deletes an address using its address ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid address ID"),
			@ApiResponse(responseCode = "404", description = "Address not found") })
	@DeleteMapping("/deleteAddress")
	public ResponseEntity<Void> deleteAddress(
			@RequestParam("addressId") @Positive(message = "Address ID must be positive") Integer addressId) {

		return addressserviceInterface.deleteAddress(addressId);
	}

	// FIND ADDRESS BY ID
	@Operation(operationId = "FindAddressById", summary = "Find address by ID", description = "Retrieves an address using its unique address ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Address found successfully"),
			@ApiResponse(responseCode = "404", description = "Address not found") })
	@GetMapping("/findAddressById")
	public ResponseEntity<Address> findAddressById(@RequestParam("addressId") @Positive Integer addressId) {

		return addressserviceInterface.findAddressById(addressId);
	}

	// FIND ALL ADDRESSES
	@Operation(operationId = "FindAllAddress", summary = "Get all addresses", description = "Retrieves the list of all saved addresses.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully") })
	@GetMapping("/findAllAddress")
	public ResponseEntity<List<Address>> findAllAddress() {

		return addressserviceInterface.findAllAddress();
	}

	// FIND BY HOUSE NUMBER
	@Operation(operationId = "FindAddressByHouseNumber", summary = "Find address by house number", description = "Retrieves an address using its house number.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Address found successfully"),
			@ApiResponse(responseCode = "404", description = "Address not found") })
	@GetMapping("/findByHouseNumber")
	public ResponseEntity<Address> findByHouseNumber(@RequestParam("houseNumber") Integer houseNumber) {

		return addressserviceInterface.findByHouseNumber(houseNumber);
	}

	// FIND BY AREA
	@Operation(operationId = "FindAddressByArea", summary = "Find addresses by area", description = "Retrieves all addresses belonging to a particular area.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses found successfully"),
			@ApiResponse(responseCode = "404", description = "No addresses found in this area") })
	@GetMapping("/findByArea")
	public ResponseEntity<List<Address>> findByArea(@RequestParam("area") String area) {

		return addressserviceInterface.findByArea(area);
	}

	// FIND BY CITY
	@Operation(operationId = "FindAddressByCity", summary = "Find addresses by city", description = "Retrieves all addresses belonging to a particular city.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses found successfully"),
			@ApiResponse(responseCode = "404", description = "No addresses found in this city") })
	@GetMapping("/findByCity")
	public ResponseEntity<List<Address>> findByCity(@RequestParam("city") String city) {

		return addressserviceInterface.findByCity(city);
	}

	// FIND BY STATE
	@Operation(operationId = "FindAddressByState", summary = "Find addresses by state", description = "Retrieves all addresses belonging to a particular state.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses found successfully"),
			@ApiResponse(responseCode = "404", description = "No addresses found in this state") })
	@GetMapping("/findByState")
	public ResponseEntity<List<Address>> findByState(@RequestParam("state") String state) {

		return addressserviceInterface.findByState(state);
	}

	// FIND BY COUNTRY
	@Operation(operationId = "FindAddressByCountry", summary = "Find addresses by country", description = "Retrieves all addresses belonging to a particular country.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses found successfully"),
			@ApiResponse(responseCode = "404", description = "No addresses found in this country") })
	@GetMapping("/findByCountry")
	public ResponseEntity<List<Address>> findByCountry(@RequestParam("country") String country) {

		return addressserviceInterface.findByCountry(country);
	}

	// FIND BY PINCODE
	@Operation(operationId = "FindAddressByPincode", summary = "Find addresses by pincode", description = "Retrieves all addresses belonging to a particular pincode.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Addresses found successfully"),
			@ApiResponse(responseCode = "404", description = "No addresses found for this pincode") })
	@GetMapping("/findByPincode")
	public ResponseEntity<List<Address>> findByPincode(@RequestParam("pincode") Integer pincode) {

		return addressserviceInterface.findByPincode(pincode);
	}
}
