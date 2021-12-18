package com.cheb.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cheb.dao.DaoProvider;
import com.cheb.dto.BookDto;
import com.cheb.dto.CreateBookDto;
import com.cheb.exception.DaoException;
import com.cheb.exception.ServiceException;
import com.cheb.exception.ValidationException;
import com.cheb.mapper.BookMapper;
import com.cheb.mapper.CreateBookMapper;
import com.cheb.validator.CreateBookValidator;

public class BookService {

	private static final BookService INSTANCE = new BookService();
	
	private final CreateBookValidator bookValidator = CreateBookValidator.getInstance();
	private final CreateBookMapper createBookMapper = CreateBookMapper.getInstance();
	private final BookMapper bookMapper = BookMapper.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();

	public BookDto create(CreateBookDto bookDto) throws ServiceException {
		var validationResult = bookValidator.isValid(bookDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
		var bookEntity = createBookMapper.mapFrom(bookDto);
		try {
			daoProvider.getBookDao().save(bookEntity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return bookMapper.mapFrom(bookEntity);
	}

	public List<BookDto> findBookByName(String name, int start, int end) throws ServiceException {
		try {
			return daoProvider.getBookDao().findByName(name, Integer.valueOf(start), Integer.valueOf(end)).stream()
					.map(bookMapper::mapFrom)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<BookDto> findBookByAuthor(String author, int start, int end) throws ServiceException {
		try {
			return daoProvider.getBookDao().findByAuthor(author, start, end).stream()
					.map(bookMapper::mapFrom)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<BookDto> findAllBooks(int start, int end) throws ServiceException {
		try {
			return daoProvider.getBookDao().findAll(Integer.valueOf(start), Integer.valueOf(end)).stream()
					.map(bookMapper::mapFrom)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public int getAllBooksCount() throws ServiceException {
		try {
			return daoProvider.getBookDao().getAllBooksCount();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public int getAllBooksByNameCount(String name) throws ServiceException {
		try {
			return daoProvider.getBookDao().getAllBooksByNameCount(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	public int getAllBooksByAuthorCount(String author) throws ServiceException {
		try {
			return daoProvider.getBookDao().getAllBooksByAuthorCount(author);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public static BookService getInstance() {
		return INSTANCE;
	}

}
