package com.lms.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LibraryDto {

	private Integer libraryId;
	
	@NotBlank@NotBlank(message = "Library Name is required")
	@NotNull(message = "Library Name is required")
	private String libraryName;
	
	@Min(6000000000L)
	@Max(9999999999L)
	private long phoneNumber;
	
}
