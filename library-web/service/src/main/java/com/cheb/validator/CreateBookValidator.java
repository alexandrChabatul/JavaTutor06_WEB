package com.cheb.validator;

import com.cheb.dto.CreateBookDto;
import com.cheb.entity.BookType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateBookValidator implements Validator<CreateBookDto>{

	private static final CreateBookValidator INSTANCE = new CreateBookValidator();

	@Override
	public ValidationResult isValid(CreateBookDto object) {
		
		var validationResult = new ValidationResult();
						
		if (object.getName().isEmpty()) {
			validationResult.add(Error.of("Empty.Book", "Book name is empty."));
		}
		
		if (BookType.find(object.getType()).isEmpty()) {
			validationResult.add(Error.of("Invalid.Type", "Type is invalid."));
		}
		
		return validationResult;
	}
	
	public static CreateBookValidator getInstance() {
		return INSTANCE;
	}
	
	
}
