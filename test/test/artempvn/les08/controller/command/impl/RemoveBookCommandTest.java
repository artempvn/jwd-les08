package test.artempvn.les08.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.impl.RemoveBookCommand;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.dao.impl.BookListDaoImpl;
import test.artempvn.les08.data.StaticData;

public class RemoveBookCommandTest {
	RemoveBookCommand removeBookCommand;

	@BeforeClass
	public void setUp() throws DaoException {
		removeBookCommand = new RemoveBookCommand();
		BookListDaoImpl.getInstance().add(StaticData.book21Test);
	}

	@Test(dataProvider = "executeTestPositive")
	public void executeTestPositive(Map<String, String> input, boolean value)
			throws ControllerException {
		Map<String, Object> actual = removeBookCommand.execute(input);
		Map<String, Object> expected = new HashMap<>();
		expected.put("Book was removed", value);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	Object[][] executeTestPositive() {
		return new Object[][] { { StaticData.bookMap21, true },
				{ StaticData.bookMap1, false } };
	}

	@AfterClass
	public void tierDown() {
		removeBookCommand = null;
	}
}
