package com.lms.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Integer userId;
	
	@NotBlank(message = "Username is required")
	@NotNull(message = "Username is required")
	private String userName;
	
	@Min(6000000000L)
	@Max(9999999999L)
	private long phoneNumber;
	
	@Email(message = "Enter a valid email address")
	private String email;
	
}
