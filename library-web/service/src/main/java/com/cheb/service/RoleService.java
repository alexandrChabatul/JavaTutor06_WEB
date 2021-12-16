package com.cheb.service;

import java.util.List;

import com.cheb.exception.ServiceException;

public interface RoleService<K, V> {

	List<V> findBooksByName(K name, int start, int end) throws ServiceException;

	List<V> findBooksByAuthor(K author, int start, int end) throws ServiceException;

	List<V> findAllBooks(int start, int end) throws ServiceException;

}
