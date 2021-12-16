package com.cheb.exception;

import java.util.List;

import com.cheb.validator.Error;
import lombok.Getter;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 2216807554052669104L;
	@Getter
	private final List<Error> errors;

	public ValidationException(List<Error> list) {
		super();
		this.errors = list;
	}

}
