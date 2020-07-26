package test.artempvn.les08.parser;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.parser.BookMapParser;
import by.artempvn.les08.request.BookDataRequest;
import test.artempvn.les08.data.StaticData;

public class BookMapParserTest {
	BookMapParser bookMapParser;

	@BeforeClass
	public void setUp() {
		bookMapParser = new BookMapParser();
	}

	@Test
	public void parseMapDataTestPositive() throws ControllerException {
		BookDataRequest actual = bookMapParser
				.parseMapData(StaticData.bookMap1);
		BookDataRequest expected = new BookDataRequest(1, "title",
				Arrays.asList("author one"), 310, 1998);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "parseMapDataTestNegative",
			expectedExceptions = ControllerException.class)
	public void parseMapDataTestNegative(Map<String, String> map)
			throws ControllerException {
		bookMapParser.parseMapData(map);
	}

	@DataProvider
	public Object[][] parseMapDataTestNegative() {
		return new Object[][] { { StaticData.mapTestFalse1 },
				{ StaticData.mapTestFalse2 }, { StaticData.mapTestFalse3 } };
	}

	@AfterClass
	public void tierDown() {
		bookMapParser = null;
	}
}
