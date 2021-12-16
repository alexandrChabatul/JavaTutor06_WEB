package com.cheb.service;

import java.util.Optional;
import com.cheb.crypto.PasswordEncryptionService;
import com.cheb.dao.DaoProvider;
import com.cheb.dao.UserDao;
import com.cheb.dto.CreateUserDto;
import com.cheb.dto.UserDto;
import com.cheb.entity.User;
import com.cheb.exception.DaoException;
import com.cheb.exception.ServiceException;
import com.cheb.exception.ValidationException;
import com.cheb.mapper.CreateUserMapper;
import com.cheb.mapper.UserMapper;
import com.cheb.validator.CreateUserValidator;

public class UserService {


	private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserMapper userMapper = UserMapper.getInstance();
	private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
	private final PasswordEncryptionService encryptService = PasswordEncryptionService.getInstance();
	
	public Optional<UserDto> login(String email, String attemptPassword) throws ServiceException {
		UserDao userDao = daoProvider.getUserDao();
		UserDto userDto = null;
		try {
			var user = userDao.findByEmail(email);
			if (user.isPresent()) {
				var userEntity = user.get();
				boolean rightPass = encryptService.authenticate(attemptPassword, userEntity.getPassword(), userEntity.getSalt());
				if (rightPass) {
					userDto = userMapper.mapFrom(userEntity);
				}
			};
			return Optional.ofNullable(userDto);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public User registration(CreateUserDto createUserDto) throws ServiceException, ValidationException {
		var validationResult = createUserValidator.isValid(createUserDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
		var userEntity = createUserMapper.mapFrom(createUserDto);
		try {
			return daoProvider.getUserDao().save(userEntity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
