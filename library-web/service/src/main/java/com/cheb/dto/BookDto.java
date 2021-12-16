package com.cheb.dto;

import com.cheb.entity.BookType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

	String name;
	String author;
	BookType type;

}
