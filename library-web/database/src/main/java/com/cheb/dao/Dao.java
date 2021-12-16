package com.cheb.dao;

import com.cheb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
	
	
	List<T> findAll(K start, K end) throws DaoException;
	
	Optional<T> findById(K id);
	
	boolean delete (K id);
	
	void update (T entity);
	
	T save (T entity) throws DaoException;
	
}
