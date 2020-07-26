package by.artempvn.les08.model.dao;

import java.sql.SQLException;
import java.util.List;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.entity.Book;

public interface BookListDao {

	void add(Book book) throws DaoException;

	void remove(Book book) throws DaoException;

	void update(Book book) throws DaoException;

	List<Book> findById(long id) throws DaoException;

	List<Book> findByTitle(String title, FieldType sortByField)
			throws DaoException;

	List<Book> findByAuthor(String author, FieldType sortByField)
			throws DaoException;

	List<Book> findByNumberPages(int numberPages, FieldType sortByField)
			throws DaoException;

	List<Book> findByYearPublishing(int yearPublishing, FieldType sortByField)
			throws DaoException;

	List<Book> takeAllBooks(FieldType sortByField) throws DaoException;

	default <T extends AutoCloseable> void close(T resource)
			throws DaoException {
		if (resource != null) {
			try {
				resource.close();
			} catch (SQLException ex) {
				// log
				System.err.println("Failed to close resourse");
			} catch (Exception ex) {
				// log
				System.err.println("Unexpected exception occured");
			}
		}
	}
}
