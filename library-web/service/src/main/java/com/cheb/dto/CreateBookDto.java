package com.cheb.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBookDto {

	String name;
	String author;
	String type;
	
}
