package by.artempvn.les08.model.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.exception.ServiceException;
import by.artempvn.les08.model.creator.BookCreator;
import by.artempvn.les08.model.dao.impl.BookListDaoImpl;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.request.BookDataRequest;

public class BookService {

	private static BookService bookService;

	private BookService() {
	}

	public static BookService getInstance() {
		if (bookService == null) {
			bookService = new BookService();
		}
		return bookService;
	}

	public void add(BookDataRequest bookData) throws ServiceException {
		BookCreator bookCreator = new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		if (book.isPresent()) {
			try {
				BookListDaoImpl.getInstance().add(book.get());
			} catch (DaoException e) {
				throw new ServiceException("Failed to add book", e);
			}
		} else {
			throw new ServiceException("Invalid data input");
		}
	}

	public void remove(BookDataRequest bookData) throws ServiceException {
		BookCreator bookCreator = new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		if (book.isPresent()) {
			try {
				BookListDaoImpl.getInstance().remove(book.get());
			} catch (DaoException e) {
				throw new ServiceException("Failed to remove book", e);
			}
		} else {
			throw new ServiceException("Invalid data input");
		}
	}

	public void update(BookDataRequest bookData) throws ServiceException {
		BookCreator bookCreator = new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		if (book.isPresent()) {
			try {
				BookListDaoImpl.getInstance().update(book.get());
			} catch (DaoException e) {
				throw new ServiceException("Failed to update book", e);
			}
		} else {
			throw new ServiceException("Invalid data input");
		}
	}

	public List<Book> findById(long id) throws ServiceException {
		List<Book> searchedBook = Collections.emptyList();
		try {
			searchedBook = BookListDaoImpl.getInstance().findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return searchedBook;
	}

	public List<Book> findByTitle(String title, FieldType sortByField)
			throws ServiceException {
		List<Book> searchedBooks = Collections.emptyList();
		try {
			searchedBooks = BookListDaoImpl.getInstance().findByTitle(title,
					sortByField);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return searchedBooks;
	}

	public List<Book> findByAuthor(String author, FieldType sortByField)
			throws ServiceException {
		List<Book> searchedBooks = Collections.emptyList();
		try {
			searchedBooks = BookListDaoImpl.getInstance().findByAuthor(author,
					sortByField);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return searchedBooks;
	}

	public List<Book> findByNumberPages(int numberPages, FieldType sortByField)
			throws ServiceException {
		List<Book> searchedBooks = Collections.emptyList();
		try {
			searchedBooks = BookListDaoImpl.getInstance()
					.findByNumberPages(numberPages, sortByField);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return searchedBooks;
	}

	public List<Book> findByYearPublishing(int yearPublishing,
			FieldType sortByField) throws ServiceException {
		List<Book> searchedBooks = Collections.emptyList();
		try {
			searchedBooks = BookListDaoImpl.getInstance()
					.findByYearPublishing(yearPublishing, sortByField);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return searchedBooks;
	}

	public List<Book> takeAllBooks(FieldType sortByField)
			throws ServiceException {
		List<Book> books = Collections.emptyList();
		try {
			books = BookListDaoImpl.getInstance().takeAllBooks(sortByField);
		} catch (DaoException e) {
			throw new ServiceException("Failed to find books", e);
		}
		return books;
	}
}
