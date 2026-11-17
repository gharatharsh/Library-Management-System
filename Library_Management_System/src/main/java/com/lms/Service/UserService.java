
package com.lms.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Dto.UserDto;
import com.lms.repository.UserRepository;
import com.lms.util.LibraryMapper;
import com.lms.entity.User;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

	private final LibraryMapper libraryMapper;
	private final UserRepository userRepository;

	// CREATE
	@Override
	public ResponseEntity<Void> saveUser(UserDto userDto) {

		// Convert UserDto to User entity
		User user = libraryMapper.convertUserDtoToEntity(userDto);

		// Save User
		userRepository.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Data Saved Successfully");

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}

	// UPDATE
	@Override
	public ResponseEntity<Void> updateUser(UserDto userDto) {

		if (userDto.getUserId() == null) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "User ID is required");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
		}

		Optional<User> optional = userRepository.findById(userDto.getUserId());

		if (optional.isPresent()) {

			User user = optional.get();

			User updatedUser = libraryMapper.convertUserDtoToEntity(userDto);

			// Update your User entity fields here.
			// Example:
			// user.setUserName(updatedUser.getUserName());

			// Copy other User fields from updatedUser into user here.

			userRepository.save(user);

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "User Updated Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Not Found");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
	}

	// DELETE
	@Override
	public ResponseEntity<Void> deleteUser(Integer userId) {

		if (!userRepository.existsById(userId)) {

			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "User Not Found");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
		}

		userRepository.deleteById(userId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Deleted Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	}

	// READ BY ID
	@Override
	public ResponseEntity<User> findUserById(Integer userId) {

		Optional<User> optional = userRepository.findById(userId);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			User user = optional.get();

			headers.add("message", "User Found Successfully for this ID");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
		} else {

			// headers.add("message", "User Not Found for this ID");
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
			throw new UserNotFoundException("User not found for this id" + userId);
		}
	}

	// READ ALL
	@Override
	public ResponseEntity<List<User>> findAllUser() {

		List<User> userList = userRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@Override
	public ResponseEntity<List<User>> findByUserName(String userName) {

		List<User> userList = userRepository.findByUserName(userName);

		if (userList.isEmpty()) {
			throw new AddressNotFoundException("User not found with username " + userName);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User Found Successfully");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userList);
	}

	@Override
	public ResponseEntity<User> findByPhoneNumber(Long phoneNumber) {

		Optional<User> optional = userRepository.findByPhoneNumber(phoneNumber);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "User Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new UserNotFoundException("User not found with phone number " + phoneNumber);
	}

	@Override
	public ResponseEntity<User> findByEmail(String email) {

		Optional<User> optional = userRepository.findByEmail(email);

		HttpHeaders headers = new HttpHeaders();

		if (optional.isPresent()) {

			headers.add("message", "User Found Successfully");

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(optional.get());
		}

		throw new UserNotFoundException("User not found with email " + email);
	}
}
