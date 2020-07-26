package test.artempvn.les08.model.dao.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.dao.impl.BookListDaoImpl;
import by.artempvn.les08.model.entity.Book;
import test.artempvn.les08.data.StaticData;

public class BookListDaoImplTest {
	BookListDaoImpl bookListDaoImpl;

	@BeforeClass
	public void setUp() {
		bookListDaoImpl = BookListDaoImpl.getInstance();

	}

	@Test(dependsOnMethods = { "findByIdTest" })
	public void addTestPositive() throws DaoException {
		bookListDaoImpl.add(StaticData.book21Test);
		List<Book> actual = bookListDaoImpl.findById(21);
		List<Book> expected = Arrays.asList(StaticData.book21Test);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = DaoException.class)
	public void addTestNegative() throws DaoException {
		bookListDaoImpl.add(StaticData.book1TestNegative);
	}

	@Test(dependsOnMethods = { "addTestPositive" })
	public void updateTestPositive() throws DaoException {
		bookListDaoImpl.update(StaticData.book21TestUpdated);
		List<Book> actual = bookListDaoImpl.findById(21);
		List<Book> expected = Arrays.asList(StaticData.book21TestUpdated);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = DaoException.class)
	public void updateTestNegative() throws DaoException {
		bookListDaoImpl.update(StaticData.book22TestNegative);
	}

	@Test(dependsOnMethods = { "updateTestPositive" })
	public void removeTestPositive() throws DaoException {
		bookListDaoImpl.remove(StaticData.book21TestUpdated);
		List<Book> actual = bookListDaoImpl.findById(21);
		assertTrue(actual.isEmpty(), " Test failed as...");
	}

	@Test(expectedExceptions = DaoException.class)
	public void removeTestNegative() throws DaoException {
		bookListDaoImpl.remove(StaticData.book1TestNegative);
	}

	@Test(dataProvider = "findByIdTest",
			dataProviderClass = StaticData.class)
	public void findByIdTest(long id, List<Book> expected) throws DaoException {
		List<Book> actual = bookListDaoImpl.findById(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByTitleTest",
			dataProviderClass = StaticData.class)
	public void findByTitleTest(String title, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByTitle(title, FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByAuthorTest",
			dataProviderClass = StaticData.class)
	public void findByAuthorTest(String author, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByAuthor(author, FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByNumberPagesTest",
			dataProviderClass = StaticData.class)
	public void findByNumberPagesTest(int numberPages, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByNumberPages(numberPages,
				FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByYearPublishingTest",
			dataProviderClass = StaticData.class)
	public void findByYearPublishingTest(int yearPublishing,
			List<Book> expected) throws DaoException {
		List<Book> actual = bookListDaoImpl.findByYearPublishing(yearPublishing,
				FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterClass
	public void tierDown() {
		bookListDaoImpl = null;
	}
}
