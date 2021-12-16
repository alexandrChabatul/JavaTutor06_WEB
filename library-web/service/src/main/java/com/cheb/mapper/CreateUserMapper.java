package com.cheb.mapper;

import com.cheb.crypto.PasswordEncryptionService;
import com.cheb.dto.CreateUserDto;
import com.cheb.entity.Role;
import com.cheb.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User>{

	private static final CreateUserMapper INSTANCE = new CreateUserMapper();
	private static final PasswordEncryptionService encryptor = PasswordEncryptionService.getInstance();
	
	@Override
	public User mapFrom(CreateUserDto object) {
		String[] password = encryptor.getPassword(object.getPassword());
		return User.builder()
				.name(object.getName())
				.email(object.getEmail())
				.password(password[0])
				.salt(password[1])
				.role(Role.valueOf(object.getRole()))
				.build();
	}

	public static CreateUserMapper getInstance() {
		return INSTANCE;
	}

}
