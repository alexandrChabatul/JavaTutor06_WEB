package com.cheb.service;

import com.cheb.dao.DaoProvider;
import com.cheb.dto.BookDto;
import com.cheb.dto.CreateBookDto;
import com.cheb.exception.DaoException;
import com.cheb.exception.ServiceException;
import com.cheb.mail.MailService;

import java.util.List;


public class AdminRoleService extends UserRoleService {
	
	private final BookService bookService = BookService.getInstance();
	private final MailService mailService = MailService.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();

//	@Override
//	public List<BookDto> findBooksByName(String name) throws ServiceException {
//		return bookService.findBookByName(name);
//	}

//	@Override
//	public List<BookDto> findBooksByAuthor(String author) throws ServiceException {
//		return bookService.findBookByAuthor(author);
//	}

//	@Override
//	public List<BookDto> findAllBooks() throws ServiceException {
//		return bookService.findAllBooks();
//	}

	public BookDto addBook(CreateBookDto createDto) throws ServiceException {
		return bookService.create(createDto);
	}
	
	public void bookNotification(BookDto book) throws ServiceException {
		List<String> emails;
		try {
			emails = daoProvider.getUserDao().getAllUserEmails();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		mailService.sendEmail(emails, List.of(book));
	}

}
