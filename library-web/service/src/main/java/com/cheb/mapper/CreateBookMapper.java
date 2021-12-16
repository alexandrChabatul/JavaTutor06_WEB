package com.cheb.mapper;

import com.cheb.dto.CreateBookDto;
import com.cheb.entity.Book;
import com.cheb.entity.BookType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBookMapper implements Mapper<CreateBookDto, Book>{

	private static final CreateBookMapper INSTANCE = new CreateBookMapper();
	
	@Override
	public Book mapFrom(CreateBookDto object) {
		return Book.builder()
				.name(object.getName())
				.author(object.getAuthor())
				.type(BookType.valueOf(object.getType()))
				.build();
	}

	public static CreateBookMapper getInstance() {
		return INSTANCE;
	}

}
