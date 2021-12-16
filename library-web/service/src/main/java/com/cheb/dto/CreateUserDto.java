package com.cheb.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
	
	String email;
	String name;
	String password;
	String role;

}
