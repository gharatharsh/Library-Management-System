
package com.lms.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

//import com.lms.Dto.AddressDto;
//import com.lms.Dto.BookDto;
//import com.lms.Dto.LibraryDto;
//import com.lms.Dto.UserDto;

import com.lms.Dto.*;

import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.entity.Library;



import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LibraryMapper {

    private final ModelMapper modelMapper;

    // DTO to Entity

    public Address convertAddressDtoToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public Book convertBookDtoToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public User convertUserDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public Library convertLibraryDtoToEntity(LibraryDto libraryDto) {
        return modelMapper.map(libraryDto, Library.class);
    }

    // Entity to DTO

    public AddressDto convertAddressToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    public BookDto convertBookToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public LibraryDto convertLibraryToDto(Library library) {
        return modelMapper.map(library, LibraryDto.class);
    }
}
