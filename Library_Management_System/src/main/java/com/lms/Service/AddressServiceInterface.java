
package com.lms.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Dto.AddressDto;
import com.lms.entity.Address;

public interface AddressServiceInterface {

	ResponseEntity<Void> saveAddress(AddressDto addressDto);

    ResponseEntity<Void> updateAddress(AddressDto addressDto);

    ResponseEntity<Void> deleteAddress(Integer addressId);

    ResponseEntity<Address> findAddressById(Integer addressId);

    ResponseEntity<List<Address>> findAllAddress();

    ResponseEntity<Address> findByHouseNumber(Integer houseNumber);

    ResponseEntity<List<Address>> findByArea(String area);

    ResponseEntity<List<Address>> findByCity(String city);

    ResponseEntity<List<Address>> findByState(String state);

    ResponseEntity<List<Address>> findByCountry(String country);

    ResponseEntity<List<Address>> findByPincode(Integer pincode);
}