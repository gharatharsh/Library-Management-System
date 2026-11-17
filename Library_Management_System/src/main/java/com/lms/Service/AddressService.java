
package com.lms.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.util.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressServiceInterface {

	private final LibraryMapper libraryMapper;
	private final AddressRepository addressRepository;

	@Override
	public ResponseEntity<Void> saveAddress(AddressDto addressDto) {
		Address address = libraryMapper.convertAddressDtoToEntity(addressDto);

		addressRepository.save(address);

		// Send RE with status code
		// return ResponseEntity.status(HttpStatus.OK).build(); // 201

		// Send RE with status code with message
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "address saved Successfully");

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();

		// return "Address Saved Successfully";
	}

	@Override
	public ResponseEntity<Void> updateAddress(AddressDto addressDto) {

		// if (addressDto.getAddressId() == null) {
		// return "Address ID is required";
		// }

		if (addressDto.getAddressId() <= 0) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Address ID is required");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
		}

		Optional<Address> optional = addressRepository.findById(addressDto.getAddressId());

		if (optional.isPresent()) {

			Address address = libraryMapper.convertAddressDtoToEntity(addressDto);

			addressRepository.save(address);

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Address Updated Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();

			// return "Address Updated Successfully";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Not Found");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();

		// return "Address Not Found";
	}

	@Override
	public ResponseEntity<Void> deleteAddress(Integer addressId) {

		if (!addressRepository.existsById(addressId)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Address Not Found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();

			// return "Address Not Found";
		}

		addressRepository.deleteById(addressId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Address Deleted Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();

		// return "Address Deleted Successfully";
	}

	@Override
	public ResponseEntity<Address> findAddressById(Integer addressId) {

		Optional<Address> optional = addressRepository.findById(addressId);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {
			Address address = optional.get();

			// return optional.get().toString();

			headers.add("message", "address found Successfully for this id");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(address);

		} else {

			// headers.add("message", "address not found for this id");
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();

			throw new AddressNotFoundException("Address not found for this id" + addressId);
		}
	}

	@Override
	public ResponseEntity<List<Address>> findAllAddress() {

		List<Address> addressList = addressRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(addressList);

		// return addressList.toString();
	}

	
	@Override
	public ResponseEntity<Address> findByHouseNumber(Integer houseNumber) {

		Optional<Address> optional = addressRepository.findByHouseNumber(houseNumber);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "Address Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new AddressNotFoundException("Address not found for house number " + houseNumber);
	}

	
	@Override
	public ResponseEntity<List<Address>> findByArea(String area) {

		List<Address> addressList = addressRepository.findByArea(area);

		if (addressList.isEmpty()) {
			throw new AddressNotFoundException("Address not found for area " + area);
		}

		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	
	@Override
	public ResponseEntity<List<Address>> findByCity(String city) {

		List<Address> addressList = addressRepository.findByCity(city);

		if (addressList.isEmpty()) {
			throw new AddressNotFoundException("Address not found for city " + city);
		}

		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	
	@Override
	public ResponseEntity<List<Address>> findByState(String state) {

		List<Address> addressList = addressRepository.findByState(state);

		if (addressList.isEmpty()) {
			throw new AddressNotFoundException("Address not found for state " + state);
		}

		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	
	@Override
	public ResponseEntity<List<Address>> findByCountry(String country) {

		List<Address> addressList = addressRepository.findByCountry(country);

		if (addressList.isEmpty()) {
			throw new AddressNotFoundException("Address not found for country " + country);
		}

		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	
	@Override
	public ResponseEntity<List<Address>> findByPincode(Integer pincode) {

		List<Address> addressList = addressRepository.findByPincode(pincode);

		if (addressList.isEmpty()) {
			throw new AddressNotFoundException("Address not found for pincode " + pincode);
		}

		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

}
