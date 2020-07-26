package by.artempvn.les08.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.connection.LibraryConnector;
import by.artempvn.les08.model.creator.BookCreator;
import by.artempvn.les08.model.dao.BookListDao;
import by.artempvn.les08.model.entity.Book;

public class BookListDaoImpl implements BookListDao {

	private static final String SEARCH_STRING_PATTERN = "%%%s%%";
	private static final int DEFAULT_ID = -1;
	private static final String DELIMITER = ", ";
	private static final String ADD_BOOK_SQL =
			"INSERT INTO books (id,title,authors,number_of_pages,year_of_publishing)"
			+ " VALUES (?,?,?,?,?)";
	private static final String ADD_BOOK_SQL_WITHOUT_ID =
			"INSERT INTO books (title,authors,number_of_pages,year_of_publishing)"
			+ " VALUES (?,?,?,?)";
	private static final String SELECT_ALL_SQL =
			"SELECT id,title,authors,number_of_pages,year_of_publishing FROM books ";
	private static final String FIND_ID_SQL = "WHERE id=%d";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM books WHERE id=%d ";
	private static final String FIND_TITLE_SQL = "WHERE title LIKE ? ";
	private static final String FIND_AUTHOR_SQL = "WHERE authors LIKE ? ";
	private static final String FIND_NUMBER_OF_PAGES_SQL =
			"WHERE number_of_pages=%d ";
	private static final String FIND_YEAR_OF_PUBLISHING_SQL = 
			"WHERE year_of_publishing=%d ";
	private static final String UPDATE_BY_ID_SQL =
			"UPDATE books SET title=?, authors=?, number_of_pages=?, "
			+ "year_of_publishing=? WHERE id=? ";

	private static BookListDaoImpl daoImpl;

	private BookListDaoImpl() {
	}

	public static BookListDaoImpl getInstance() {
		if (daoImpl == null) {
			daoImpl = new BookListDaoImpl();
		}
		return daoImpl;
	}

	@Override
	public void add(Book book) throws DaoException {
		if (findById(book.getId()).isEmpty()) {
			PreparedStatement statement = null;
			try (Connection connection = LibraryConnector.getInstance()
					.getDataSource().getConnection()) {
				int parametrIndex = 1;
				if (book.getId() == DEFAULT_ID) {
					statement = connection.prepareStatement(
							ADD_BOOK_SQL_WITHOUT_ID,
							Statement.RETURN_GENERATED_KEYS);
					statement.getGeneratedKeys();
				} else {
					statement = connection.prepareStatement(ADD_BOOK_SQL);
					statement.setLong(parametrIndex++, book.getId());
				}
				statement.setString(parametrIndex++, book.getTitle());
				statement.setString(parametrIndex++,
						String.join(DELIMITER, book.getAuthorsReadOnly()));
				statement.setInt(parametrIndex++, book.getNumberPages());
				statement.setInt(parametrIndex, book.getYearPublishing());
				statement.execute();
			} catch (SQLException ex) {
				throw new DaoException("Failed to add book", ex);
			} finally {
				close(statement);
			}
		} else {
			throw new DaoException("Book with such id is already exists");
		}
	}

	@Override
	public void remove(Book book) throws DaoException {
		List<Book> foundBook = findById(book.getId());
		if (!foundBook.isEmpty()) {
			if (foundBook.get(0).equals(book)) {
				String sql = String.format(DELETE_BY_ID_SQL, book.getId());
				try (Connection connection = LibraryConnector.getInstance()
						.getDataSource().getConnection();
						PreparedStatement statement = connection
								.prepareStatement(sql)) {
					statement.execute();
				} catch (SQLException ex) {
					throw new DaoException("Failed to remove book", ex);
				}
			} else {
				throw new DaoException("Book with such data doesn't exist");
			}
		} else {
			throw new DaoException("Book with such id doesn't exist");
		}
	}

	@Override
	public void update(Book book) throws DaoException {
		List<Book> foundBook = findById(book.getId());
		if (!foundBook.isEmpty()) {
			try (Connection connection = LibraryConnector.getInstance()
					.getDataSource().getConnection();
					PreparedStatement statement = connection
							.prepareStatement(UPDATE_BY_ID_SQL)) {
				statement.setString(1, book.getTitle());
				statement.setString(2,
						String.join(DELIMITER, book.getAuthorsReadOnly()));
				statement.setInt(3, book.getNumberPages());
				statement.setInt(4, book.getYearPublishing());
				statement.setLong(5, book.getId());
				statement.execute();
			} catch (SQLException ex) {
				throw new DaoException("Failed to update book", ex);
			}
		} else {
			throw new DaoException("Book with such id doesn't exist");
		}
	}

	@Override
	public List<Book> findById(long searchedId) throws DaoException {
		List<Book> searchedBook = Collections.emptyList();
		String sql = String.format(SELECT_ALL_SQL + FIND_ID_SQL, searchedId);
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			searchedBook = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to find book by id", ex);
		}
		return searchedBook;
	}

	@Override
	public List<Book> findByTitle(String title, FieldType sortByField)
			throws DaoException {
		List<Book> searchedBooks = Collections.emptyList();
		ResultSet resultSet = null;
		String sql = SELECT_ALL_SQL + FIND_TITLE_SQL + sortByField.getSqlSort();
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(sql)) {
			String preparedTitle = String.format(SEARCH_STRING_PATTERN, title);
			statement.setString(1, preparedTitle);
			resultSet = statement.executeQuery();
			searchedBooks = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to find book by title", ex);
		} finally {
			close(resultSet);
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByAuthor(String searchedAuthor, FieldType sortByField)
			throws DaoException {
		List<Book> searchedBooks = Collections.emptyList();
		ResultSet resultSet = null;
		String sql = SELECT_ALL_SQL + FIND_AUTHOR_SQL
				+ sortByField.getSqlSort();
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(sql);) {
			String preparedAuthor = String.format(SEARCH_STRING_PATTERN,
					searchedAuthor);
			statement.setString(1, preparedAuthor);
			resultSet = statement.executeQuery();
			searchedBooks = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to find book by author", ex);
		} finally {
			close(resultSet);
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByNumberPages(int numberPages, FieldType sortByField)
			throws DaoException {
		List<Book> searchedBooks = Collections.emptyList();
		String sql = String.format(SELECT_ALL_SQL + FIND_NUMBER_OF_PAGES_SQL
				+ sortByField.getSqlSort(), numberPages);
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			searchedBooks = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to find book by number of pages",
					ex);
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByYearPublishing(int yearPublishing,
			FieldType sortByField) throws DaoException {
		List<Book> searchedBooks = Collections.emptyList();
		String sql = String.format(SELECT_ALL_SQL + FIND_YEAR_OF_PUBLISHING_SQL
				+ sortByField.getSqlSort(), yearPublishing);
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			searchedBooks = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to find book by year of publishment",
					ex);
		}
		return searchedBooks;
	}

	@Override
	public List<Book> takeAllBooks(FieldType sortByField) throws DaoException {
		List<Book> searchedBooks = Collections.emptyList();
		try (Connection connection = LibraryConnector.getInstance()
				.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SELECT_ALL_SQL + sortByField.getSqlSort());
				ResultSet resultSet = statement.executeQuery()) {
			searchedBooks = executeBooks(resultSet);
		} catch (SQLException ex) {
			throw new DaoException("Failed to take books from database", ex);
		}
		return searchedBooks;
	}

	private List<Book> executeBooks(ResultSet resultSet) throws SQLException {
		List<Book> searchedBooks = new ArrayList<>();
		BookCreator bookCreator = new BookCreator();
		while (resultSet.next()) {
			searchedBooks.add(bookCreator.createBookByResultSet(resultSet));
		}
		return searchedBooks;
	}
}