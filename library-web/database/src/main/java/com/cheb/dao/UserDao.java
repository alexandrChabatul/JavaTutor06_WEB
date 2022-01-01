package com.cheb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cheb.entity.Role;
import com.cheb.entity.User;
import com.cheb.exception.DaoException;
import com.cheb.util.ConnectionManager;

public class UserDao implements Dao<Integer, User> {

	private static final String SAVE_SQL = "INSERT INTO users (name, email, password, salt, role)"
			+ "VALUES (?, ?, ?, ?, ?);";
	private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM users WHERE email = ? and password = ?;";
	private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?;";
	private static final String CHECK_EMAIL_SQL = "SELECT 1 FROM users WHERE email = ? LIMIT 1;";
	private static final String FIND_ALL_EMAILS_SQL = "SELECT email FROM users WHERE role = 'USER';";

	public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, password);

			var executeQuery = prepareStatement.executeQuery();

			User user = null;

			if (executeQuery.next()) {
				user = createUser(executeQuery);
			}
			return Optional.ofNullable(user);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Optional<User> findById(Integer id) {
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public void update(User entity) {
	}

	@Override
	public User save(User entity) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			prepareStatement.setObject(1, entity.getName());
			prepareStatement.setObject(2, entity.getEmail());
			prepareStatement.setObject(3, entity.getPassword());
			prepareStatement.setObject(4, entity.getSalt());
			prepareStatement.setObject(5, entity.getRole().name());

			prepareStatement.executeUpdate();

			var resultSet = prepareStatement.getGeneratedKeys();
			resultSet.next();
			entity.setId(resultSet.getObject("id", Integer.class));

			return entity;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Optional<User> findByEmail(String email) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
			prepareStatement.setString(1, email);

			var executeQuery = prepareStatement.executeQuery();

			User user = null;

			if (executeQuery.next()) {
				user = createUser(executeQuery);
			}
			return Optional.ofNullable(user);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public boolean checkEmail(String email) throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(CHECK_EMAIL_SQL)) {
			prepareStatement.setString(1, email);

			var executeQuery = prepareStatement.executeQuery();

			if (executeQuery.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<String> getAllUserEmails() throws DaoException {
		try (var connection = ConnectionManager.get();
				var prepareStatement = connection.prepareStatement(FIND_ALL_EMAILS_SQL)) {

			var executeQuery = prepareStatement.executeQuery();

			List<String> emails = new ArrayList<>();

			if (executeQuery.next()) {
				emails.add(executeQuery.getObject("email", String.class));
			}
			return emails;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	private User createUser(ResultSet executeQuery) throws SQLException {
		return User.builder().id(executeQuery.getObject("id", Integer.class))
				.name(executeQuery.getObject("name", String.class)).email(executeQuery.getObject("email", String.class))
				.password(executeQuery.getObject("password", String.class))
				.salt(executeQuery.getObject("salt", String.class))
				.role(Role.valueOf(executeQuery.getObject("role", String.class))).build();
	}

	@Override
	public List<User> findAll(Integer start, Integer end) throws DaoException {
		return null;
	}

}
