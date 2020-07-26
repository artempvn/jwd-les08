package by.artempvn.les08.model.creator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import by.artempvn.les08.model.dao.ColumnName;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.request.BookDataRequest;
import by.artempvn.les08.validator.BookValidator;

public class BookCreator {
	private static final String DELIMITER = ", ";

	public Optional<Book> createBook(BookDataRequest bookData) {
		BookValidator validator = new BookValidator();
		Optional<Book> book = Optional.empty();
		long id = bookData.getId();
		String title = bookData.getTitle();
		List<String> authors = bookData.getAuthors();
		int numberPages = bookData.getNumberPages();
		int yearPublishing = bookData.getYearPublishing();
		boolean correctInput = (validator.isIdCorrect(id)
				&& validator.isTitleCorrect(title)
				&& validator.isAuthorsCorrect(authors)
				&& validator.isNumberPagesCorrect(numberPages)
				&& validator.isYearPublishingCorrect(yearPublishing));
		if (correctInput) {
			book = Optional.of(
					new Book(id, title, authors, numberPages, yearPublishing));
		}
		return book;
	}

	public Book createBookByResultSet(ResultSet resultSet) throws SQLException {
		Book book;
		long id = resultSet.getLong(ColumnName.COLUMN_LIBRARY_1);
		String title = resultSet.getString(ColumnName.COLUMN_LIBRARY_2);
		List<String> authors = Arrays.asList(resultSet
				.getString(ColumnName.COLUMN_LIBRARY_3).split(DELIMITER));
		int numberPages = resultSet.getInt(ColumnName.COLUMN_LIBRARY_4);
		int yearPublishing = resultSet.getInt(ColumnName.COLUMN_LIBRARY_5);
		book = new Book(id, title, authors, numberPages, yearPublishing);
		return book;
	}
}
