package test.artempvn.les08.validator;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.validator.BookValidator;

public class BookValidatorTest {
	BookValidator bookValidator;

	@BeforeClass
	public void setUp() {
		bookValidator = new BookValidator();
	}

	@Test(dataProvider = "isIdCorrectTest")
	public void isIdCorrectTest(long id, boolean expected) {
		boolean actual = bookValidator.isIdCorrect(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isIdCorrectTest() {
		return new Object[][] { { 10, true }, { -5, false } };
	}

	@Test(dataProvider = "isTitleCorrectTest")
	public void isTitleCorrectTest(String title, boolean expected) {
		boolean actual = bookValidator.isTitleCorrect(title);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isTitleCorrectTest() {
		return new Object[][] { { "correct title", true },
				{ "_incorrect", false } };
	}

	@Test(dataProvider = "isAuthorCorrectTest")
	public void isAuthorCorrectTest(String author, boolean expected) {
		boolean actual = bookValidator.isAuthorCorrect(author);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isAuthorCorrectTest() {
		return new Object[][] { { "correct author", true },
				{ "aut4or", false } };
	}

	@Test(dataProvider = "isAuthorsCorrectTest")
	public void isAuthorCorrectTest(List<String> authors, boolean expected) {
		boolean actual = bookValidator.isAuthorsCorrect(authors);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isAuthorsCorrectTest() {
		return new Object[][] {
				{ Arrays.asList("Author One", "Author Two"), true },
				{ Arrays.asList("Author 1"), false },
				{ Arrays.asList(), false } };
	}

	@Test(dataProvider = "isNumberPagesCorrectTest")
	public void isNumberPagesCorrectTest(int numberPages, boolean expected) {
		boolean actual = bookValidator.isNumberPagesCorrect(numberPages);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isNumberPagesCorrectTest() {
		return new Object[][] { { 30, true }, { 10, false },
				{ 10_500, false } };
	}

	@Test(dataProvider = "isYearPublishingCorrectTest")
	public void isYearPublishingCorrectTest(int yearPublishing,
			boolean expected) {
		boolean actual = bookValidator.isYearPublishingCorrect(yearPublishing);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isYearPublishingCorrectTest() {
		return new Object[][] { { 2020, true }, { 1816, false },
				{ 2025, false } };
	}

	@AfterClass
	public void tierDown() {
		bookValidator = null;
	}
}
