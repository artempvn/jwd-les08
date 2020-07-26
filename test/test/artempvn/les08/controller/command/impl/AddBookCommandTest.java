package test.artempvn.les08.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.impl.AddBookCommand;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.dao.impl.BookListDaoImpl;
import test.artempvn.les08.data.StaticData;

public class AddBookCommandTest {
	AddBookCommand addBookCommand;

	@BeforeClass
	public void setUp() {
		addBookCommand = new AddBookCommand();
	}

	@Test(dataProvider = "executeTestPositive")
	public void executeTestPositive(Map<String, String> input, boolean value)
			throws ControllerException {
		Map<String, Object> actual = addBookCommand.execute(input);
		Map<String, Object> expected = new HashMap<>();
		expected.put("Book was added", value);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	Object[][] executeTestPositive() {
		return new Object[][] { { StaticData.bookMap21, true },
				{ StaticData.bookMap1, false } };
	}

	@AfterClass
	public void tierDown() throws DaoException {
		BookListDaoImpl.getInstance().remove(StaticData.book21Test);
		addBookCommand = null;
	}
}
