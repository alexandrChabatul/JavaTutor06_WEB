package com.cheb.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

	private Integer id;
	private String name;
	private String author;
	private BookType type;

}
