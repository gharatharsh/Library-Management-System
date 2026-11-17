package com.lms.Dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated

public class AddressDto {
	
	private int addressId;
	
	@NotNull(message = "House Number is required")
	@Min(value = 1, message = "House Number is must")
	
	@NotBlank(message = "Area Should be Correct")
	private String area;
	
	@NotBlank(message = "City Should be Correct")
	private String city;
	
	@NotBlank(message = "State Should be Correct")
	private String state;
	
	@NotBlank(message = "Country Should be Correct")
	private String country;
	
	@Digits(integer = 6,fraction = 0)
	private int pincode;
	
}
