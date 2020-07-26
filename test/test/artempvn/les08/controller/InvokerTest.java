package test.artempvn.les08.controller;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.Invoker;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.model.entity.Book;
import test.artempvn.les08.data.StaticData;

public class InvokerTest {
	Invoker invoker;
	Map<String, Object> addMap;
	Map<String, Object> removeMap;
	Map<String, Object> updateMap;
	Map<String, Object> findMap;

	@BeforeClass
	public void setUp() {
		invoker = Invoker.getInstance();
		addMap = new HashMap<String, Object>();
		addMap.put("Book was added", false);
		removeMap = new HashMap<String, Object>();
		removeMap.put("Book was removed", false);
		updateMap = new HashMap<String, Object>();
		updateMap.put("Book was updated", false);
		findMap = new HashMap<String, Object>();
		findMap.put("Found books", Arrays.asList(StaticData.book20));
	}

	@Test(dataProvider = "processRequestTestPositive")
	public void processRequestTestPositive(String command,
			Map<String, String> params, Map<String, Object> expectedMap)
			throws ControllerException {
		Optional<Map<String, Object>> actual = invoker.processRequest(command,
				params);
		Optional<Map<String, Object>> expected = Optional.of(expectedMap);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] processRequestTestPositive() {
		return new Object[][] { { "ADD_BOOK", StaticData.bookMap1, addMap },
				{ "REMOVE_BOOK", StaticData.bookMap1, removeMap },
				{ "UPDATE_BOOK", StaticData.bookMap21, updateMap },
				{ "FIND_BY_TAG", new HashMap<String, String>() {
					{
						put("tag", "NUMBER_PAGES");
						put("value", "896");
						put("sort", "id");
					}
				}, findMap } };
	}

	@Test
	public void processRequestTakeAllTestPositive() throws ControllerException {
		Optional<Map<String, Object>> actualMap = invoker.processRequest(
				"TAKE_all_Books", new HashMap<String, String>() {
					{
						put("sort", "id");
					}
				});
		int actual = ((List<Book>) actualMap.get().get("Found books")).size();
		int expected = 20;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "processRequestTestNegative", expectedExceptions = ControllerException.class)
	public void processRequestTestNegative(String command)
			throws ControllerException {
		invoker.processRequest(command, null);
	}

	@DataProvider
	public Object[][] processRequestTestNegative() {
		return new Object[][] { { "Not command" }, { null } };
	}

	@AfterClass
	public void tierDown() {
		invoker = null;
	}
}
