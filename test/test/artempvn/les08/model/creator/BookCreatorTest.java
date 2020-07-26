package test.artempvn.les08.model.creator;

import static org.testng.Assert.assertEquals;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.model.creator.BookCreator;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.request.BookDataRequest;
import test.artempvn.les08.data.StaticData;

public class BookCreatorTest {
	BookCreator bookCreator;

	@BeforeClass
	public void setUp() {
		bookCreator = new BookCreator();
	}

	@Test(dataProvider = "createBookTest")
	public void createBookTest(BookDataRequest bookData,
			Optional<Book> expected) {
		Optional<Book> actual = bookCreator.createBook(bookData);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] createBookTest() {
		return new Object[][] {
				{ StaticData.book21DataTest,
						Optional.of(StaticData.book21Test) },
				{ StaticData.bookDataTestNegative, Optional.empty() } };
	}

	@AfterClass
	public void tierDown() {
		bookCreator = null;
	}
}
