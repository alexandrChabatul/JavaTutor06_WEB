package com.cheb.service;

import com.cheb.dto.BookDto;
import com.cheb.exception.ServiceException;

import java.util.List;


public class UserRoleService implements RoleService<String, BookDto> {
	
	private final BookService bookService = BookService.getInstance();

	@Override
	public List<BookDto> findBooksByName(String name, int start, int end) throws ServiceException {
		return bookService.findBookByName(name, start, end);
	}

	@Override
	public List<BookDto> findBooksByAuthor(String author, int start, int end) throws ServiceException {
		return bookService.findBookByAuthor(author,start, end);
	}

	@Override
	public List<BookDto> findAllBooks(int start, int end) throws ServiceException {
		return bookService.findAllBooks(start, end);
	}
	
	public int countAllBook() throws ServiceException {
		return bookService.getAllBooksCount();
	}
	
	public int countAllBookByName(String name) throws ServiceException {
		return bookService.getAllBooksByNameCount(name);
	}

	public int countAllBookByAuthor(String author) throws ServiceException {
		return bookService.getAllBooksByAuthorCount(author);
	}
	
}
