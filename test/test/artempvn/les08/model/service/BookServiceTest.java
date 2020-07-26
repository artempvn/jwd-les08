package test.artempvn.les08.model.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.ServiceException;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.model.service.BookService;
import test.artempvn.les08.data.StaticData;

public class BookServiceTest {
	BookService bookService;

	@BeforeClass
	public void setUp() {
		bookService = BookService.getInstance();
	}

	@Test (dependsOnMethods = {"findByIdTest"})
	public void addTestPositive() throws ServiceException {
		bookService.add(StaticData.book21DataTest);
		List<Book> actual=bookService.findById(21);
		List<Book> expected = Arrays.asList(StaticData.book21Test);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addTestNegative()
			throws ServiceException {
		bookService.add(StaticData.book1DataTestNegative);
	}
	
	@Test (dependsOnMethods = {"addTestPositive"})
	public void updateTestPositive() throws ServiceException {
		bookService.update(StaticData.book21DataTestUpdated);
		List<Book> actual=bookService.findById(21);
		List<Book> expected = Arrays.asList(StaticData.book21TestUpdated);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void updateTestNegative()
			throws ServiceException {
		bookService.update(StaticData.book22DataTestNegative);
	}

	@Test(dependsOnMethods = {"updateTestPositive"})
	public void removeTestPositive() throws ServiceException {
		bookService.remove(StaticData.book21DataTestUpdated);
		List<Book> actual = bookService.findById(21);
		assertTrue(actual.isEmpty(), " Test failed as...");
	}

	@Test(
			expectedExceptions = ServiceException.class)
	public void removeTestNegative()
			throws ServiceException {
		bookService.remove(StaticData.book22DataTestNegative);
	}

	@Test(dataProvider = "findByIdTest", dataProviderClass = StaticData.class)
	public void findByIdTest(long id, List<Book> expected)
			throws ServiceException {
		List<Book> actual =
				bookService.findById(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByTitleTest",
			dataProviderClass = StaticData.class)
	public void findByTitleTest(String title, List<Book> expected)
			throws ServiceException {
		List<Book> actual =
				bookService.findByTitle(title, FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByAuthorTest",
			dataProviderClass = StaticData.class)
	public void findByAuthorTest(String author, List<Book> expected) 
			throws ServiceException {
		List<Book> actual =
				bookService.findByAuthor(author,FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByNumberPagesTest",
			dataProviderClass = StaticData.class)
	public void findByNumberPagesTest(int numberPages, List<Book> expected)
			throws ServiceException {
		List<Book> actual = 
				bookService.findByNumberPages(numberPages,FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByYearPublishingTest",
			dataProviderClass = StaticData.class)
	public void findByYearPublishingTest(int yearPublishing,
			List<Book> expected) throws ServiceException {
		List<Book> actual = 
				bookService.findByYearPublishing(yearPublishing,FieldType.ID);
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterClass
	public void tierDown() {
		bookService = null;
	}
}
