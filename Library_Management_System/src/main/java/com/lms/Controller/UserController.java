
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

import com.lms.Dto.UserDto;
import com.lms.Service.UserServiceInterface;
import com.lms.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserServiceInterface userServiceInterface;

	// CREATE USER
	@Operation(operationId = "CreateUser", summary = "Create new user", description = "Creates and saves a new user in the library management system.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid user details") })
	@PostMapping("/saveUser")
	public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDto userDto) {

		return userServiceInterface.saveUser(userDto);
	}

	// UPDATE USER
	@Operation(operationId = "UpdateUser", summary = "Update user", description = "Updates the details of an existing user.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid user details or user ID"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@PutMapping("/updateUser")
	public ResponseEntity<Void> updateUser(@Valid @RequestBody UserDto userDto) {

		return userServiceInterface.updateUser(userDto);
	}

	// DELETE USER
	@Operation(operationId = "DeleteUser", summary = "Delete user", description = "Deletes an existing user using the user ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid user ID"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void> deleteUser(@Valid @RequestParam("userId") @Positive Integer userId) {

		return userServiceInterface.deleteUser(userId);
	}

	// FIND USER BY ID
	@Operation(operationId = "FindUserById", summary = "Find user by ID", description = "Retrieves a user using the unique user ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User found successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid user ID"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@GetMapping("/findUserById")
	public ResponseEntity<User> findUserById(@Valid @RequestParam("userId") @Positive Integer userId) {

		return userServiceInterface.findUserById(userId);
	}

	// FIND ALL USERS
	@Operation(operationId = "FindAllUser", summary = "Get all users", description = "Retrieves the list of all registered users.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Users retrieved successfully") })
	@GetMapping("/findAllUser")
	public ResponseEntity<List<User>> findAllUser() {

		return userServiceInterface.findAllUser();
	}

	// FIND BY USERNAME
	@Operation(operationId = "FindUserByUserName", summary = "Find users by username", description = "Retrieves users matching the specified username.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User found successfully"),
			@ApiResponse(responseCode = "404", description = "User not found with this username") })
	@GetMapping("/findByUserName")
	public ResponseEntity<List<User>> findByUserName(@Valid @RequestParam("userName") String userName) {

		return userServiceInterface.findByUserName(userName);
	}

	// FIND BY PHONE NUMBER
	@Operation(operationId = "FindUserByPhoneNumber", summary = "Find user by phone number", description = "Retrieves a user using the registered phone number.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User found successfully"),
			@ApiResponse(responseCode = "404", description = "User not found with this phone number") })
	@GetMapping("/findUserByPhoneNumber")
	public ResponseEntity<User> findByPhoneNumber(@RequestParam("phoneNumber") Long phoneNumber) {

		return userServiceInterface.findByPhoneNumber(phoneNumber);
	}

	// FIND BY EMAIL
	@Operation(operationId = "FindUserByEmail", summary = "Find user by email", description = "Retrieves a user using the registered email address.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User found successfully"),
			@ApiResponse(responseCode = "404", description = "User not found with this email") })
	@GetMapping("/findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {

		return userServiceInterface.findByEmail(email);
	}
}
