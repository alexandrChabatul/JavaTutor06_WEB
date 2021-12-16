package com.cheb.validator;

import com.cheb.exception.ServiceException;

public interface Validator<T> {

	ValidationResult isValid(T object) throws ServiceException;
	
}
