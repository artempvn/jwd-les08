package test.artempvn.les08.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.impl.TakeAllBooksCommand;
import by.artempvn.les08.model.entity.Book;

public class TakeAllBooksCommandTest {
	TakeAllBooksCommand takeAllBooksCommand;
	Map<String, String> params1;

	@BeforeClass
	public void setUp() {
		takeAllBooksCommand = new TakeAllBooksCommand();
		params1 = new HashMap<>();
		params1.put("sort", "ID");
	}

	@Test
	public void executeTest() {
		Map<String, Object> actualMap = takeAllBooksCommand.execute(params1);
		int actual = ((List<Book>) actualMap.get("Found books")).size();
		int expected = 20;
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterClass
	public void tierDown() {
		takeAllBooksCommand = null;
		params1 = null;
	}
}
