package com.cheb.mapper;

import com.cheb.dto.UserDto;
import com.cheb.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto>{
	
		private static final UserMapper INSTANCE = new UserMapper();

	@Override
	public UserDto mapFrom(User object) {
		return UserDto.builder()
				.email(object.getEmail())
				.name(object.getName())
				.role(object.getRole())
				.build();
	}
	
	public static UserMapper getInstance() {
		return INSTANCE;
	}
}
