package com.cheb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.cheb.entity.Book;
import com.cheb.entity.BookType;
import com.cheb.exception.DaoException;
import com.cheb.util.ConnectionManager;

public class BookDao implements Dao<Integer, Book> {

	private static final String FIND_ALL_SQL = """
			SELECT *
			FROM books
			ORDER BY name ASC
			LIMIT ?
			OFFSET ?;
			""";
	private static final String COUNT_ALL_SQL = """
			SELECT COUNT (*)
			FROM books;
			""";
	private static final String SAVE_SQL = """
			INSERT INTO books (name, author, type) VALUES (?, ?, ?);
			""";
	private static final String FIND_BY_NAME_SQL = """
			SELECT *
			FROM books
			WHERE lower(name) LIKE ?
			ORDER BY name ASC
			LIMIT ?
			OFFSET ?;
			""";
	private static final String COUNT_BY_NAME_SQL = """
			SELECT COUNT (*)
			FROM books
			WHERE lower(name) LIKE ?;
			""";
	private static final String FIND_BY_AUTHOR_SQL = """
			SELECT *
			FROM books
			WHERE lower(author) LIKE ?
			ORDER BY name ASC
			LIMIT ?
			OFFSET ?;
			""";
	private static final String COUNT_BY_AUTHOR_SQL = """
			SELECT COUNT (*)
			FROM books
			WHERE lower(author) LIKE ?;
			""";

	@Override
	public List<Book> findAll(Integer start, Integer end) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
			prepareStatement.setObject(1, end);
			prepareStatement.setObject(2, start);
			var result = prepareStatement.executeQuery();
			List<Book> books = new ArrayList<>();
			while (result.next()) {
				books.add(buildBook(result));
			}
			return books;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	public int getAllBooksCount() throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(COUNT_ALL_SQL)) {
			var result1 = prepareStatement.executeQuery();
			result1.next();
			int count = result1.getInt(1);
			return count;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	@Override
	public Book save(Book entity) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			prepareStatement.setObject(1, entity.getName());
			prepareStatement.setObject(2, entity.getAuthor());
			prepareStatement.setObject(3, entity.getType().name());

			prepareStatement.executeUpdate();

			var resultSet = prepareStatement.getGeneratedKeys();
			resultSet.next();
			entity.setId(resultSet.getObject("id", Integer.class));

			return entity;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Book> findByName(String name, Integer start, Integer end) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
			prepareStatement.setObject(1, "%" + name.toLowerCase() + "%");
			prepareStatement.setObject(2, end);
			prepareStatement.setObject(3, start);
			var result = prepareStatement.executeQuery();
			List<Book> books = new ArrayList<>();
			while (result.next()) {
				books.add(buildBook(result));
			}
			return books;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int getAllBooksByNameCount(String name) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(COUNT_BY_NAME_SQL)) {
			prepareStatement.setObject(1, "%" + name.toLowerCase() + "%");
			var result1 = prepareStatement.executeQuery();
			result1.next();
			int count = result1.getInt(1);
			return count;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	public List<Book> findByAuthor(String author, Integer start, Integer end) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_BY_AUTHOR_SQL)) {
			prepareStatement.setObject(1, "%" + author.toLowerCase() + "%");
			prepareStatement.setObject(2, end);
			prepareStatement.setObject(3, start);
			var result = prepareStatement.executeQuery();
			List<Book> books = new ArrayList<>();
			while (result.next()) {
				books.add(buildBook(result));
			}
			return books;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public int getAllBooksByAuthorCount(String author) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(COUNT_BY_AUTHOR_SQL)) {
			prepareStatement.setObject(1, "%" + author.toLowerCase() + "%");
			var result1 = prepareStatement.executeQuery();
			result1.next();
			int count = result1.getInt(1);
			return count;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Optional<Book> findById(Integer id) {
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public void update(Book entity) {

	}

	private Book buildBook(ResultSet result) throws SQLException {
		return Book.builder().id(result.getObject("id", Integer.class)).name(result.getObject("name", String.class))
				.author(result.getObject("author", String.class))
				.type(BookType.valueOf(result.getObject("type", String.class))).build();
	}

}