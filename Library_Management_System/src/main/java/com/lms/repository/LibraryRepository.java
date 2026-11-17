package com.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
	
	Optional<Library> findByLibraryName(String libraryName);
	
	Optional<Library> findByPhoneNumber(long phoneNumber);
	
	
}