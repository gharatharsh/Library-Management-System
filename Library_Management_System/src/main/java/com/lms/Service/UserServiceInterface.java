
package com.lms.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Dto.UserDto;
import com.lms.entity.User;

public interface UserServiceInterface {

	ResponseEntity<Void> saveUser(UserDto userDto);

	ResponseEntity<Void> updateUser(UserDto userDto);

	ResponseEntity<Void> deleteUser(Integer userId);

	ResponseEntity<User> findUserById(Integer userId);

	ResponseEntity<List<User>> findAllUser();
	
    ResponseEntity<List<User>> findByUserName(String userName);

    ResponseEntity<User> findByPhoneNumber(Long phoneNumber);

    ResponseEntity<User> findByEmail(String email);
}

