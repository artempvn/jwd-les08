package test.artempvn.les08.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.impl.FindByTagCommand;
import by.artempvn.les08.model.entity.Book;
import test.artempvn.les08.data.StaticData;

public class FindByTagCommandTest {
	FindByTagCommand findByTagCommand;
	Map<String, String> params1;
	Map<String, String> params2;
	Map<String, String> params3;
	Map<String, String> params4;
	Map<String, String> params5;
	Map<String, String> params6;
	Map<String, String> params7;
	Map<String, String> params8;

	@BeforeClass
	public void setUp() {
		findByTagCommand = new FindByTagCommand();
		params1 = new HashMap<>();
		params1.put("tag", "ID");
		params1.put("value", "20");
		params1.put("sort", "ID");
		params2 = new HashMap<>();
		params2.put("tag", "TITLE");
		params2.put("value", "Методы");
		params2.put("sort", "ID");
		params3 = new HashMap<>();
		params3.put("tag", "AUTHORS");
		params3.put("value", "Игорь");
		params3.put("sort", "ID");
		params4 = new HashMap<>();
		params4.put("tag", "NUMBER_PAGES");
		params4.put("value", "896");
		params4.put("sort", "ID");
		params5 = new HashMap<>();
		params5.put("tag", "YEAR_PUBLISHING");
		params5.put("value", "2013");
		params5.put("sort", "ID");
		params6 = new HashMap<>();
		params6.put("tag", "ID");
		params6.put("value", "not long");
		params6.put("sort", "ID");
		params7 = new HashMap<>();
		params7.put("tag", "NUMBER_PAGES");
		params7.put("value", "not int");
		params7.put("sort", "ID");
		params8 = new HashMap<>();
		params8.put("tag", "YEAR_PUBLISHING");
		params8.put("value", "not int");
		params8.put("sort", "ID");
	}

	@Test(dataProvider = "executeTest")
	public void executeTest(Map<String, String> params,
			List<Book> expectedList) {
		Map<String, Object> actual = findByTagCommand.execute(params);
		Map<String, Object> expected = new HashMap<>();
		expected.put("Found books", expectedList);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] executeTest() {
		return new Object[][] { { params1, Arrays.asList(StaticData.book20) },
				{ params2, Arrays.asList(StaticData.book20) },
				{ params3, Arrays.asList(StaticData.book20) },
				{ params4, Arrays.asList(StaticData.book20) },
				{ params5,
						Arrays.asList(StaticData.book10, StaticData.book20) },
				{ params6, Arrays.asList() }, { params7, Arrays.asList() },
				{ params8, Arrays.asList() } };
	}

	@AfterClass
	public void tierDown() {
		findByTagCommand = null;
		params1 = null;
		params2 = null;
		params3 = null;
		params4 = null;
		params5 = null;
		params6 = null;
		params7 = null;
		params8 = null;
	}
}
