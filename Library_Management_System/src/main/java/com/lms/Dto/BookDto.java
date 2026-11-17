package com.lms.Dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookDto {

	private Integer bookId;
	
	@NotBlank(message = "Title is required")
	@NotNull(message = "Title is required")
	private String title;
	
	@NotBlank
	@NotNull
	@Size(max = 20, message = "Author cannot exceed 20 characters")
	private String author;
	
	@Size(min =1 )
	
	@NotBlank(message = "Category is required")
	private String category;
	
	
	private int copies;
	private LocalDateTime borrowTime;
	private LocalDateTime returnTime;
	
	
}
