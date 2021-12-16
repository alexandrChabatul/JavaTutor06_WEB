package com.cheb.dto;

import com.cheb.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
	
	String email;
	String name;
	Role role;

}
