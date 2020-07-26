package test.artempvn.les08.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.controller.command.impl.UpdateBookCommand;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.exception.DaoException;
import by.artempvn.les08.model.dao.impl.BookListDaoImpl;
import test.artempvn.les08.data.StaticData;

public class UpdateBookCommandTest {
	UpdateBookCommand updateBookCommand;

	@BeforeClass
	public void setUp() throws DaoException {
		updateBookCommand = new UpdateBookCommand();
		BookListDaoImpl.getInstance().add(StaticData.book21Test);
	}

	@Test(dataProvider = "executeTestPositive")
	public void executeTestPositive(Map<String, String> input, boolean value)
			throws ControllerException {
		Map<String, Object> actual = updateBookCommand.execute(input);
		Map<String, Object> expected = new HashMap<>();
		expected.put("Book was updated", value);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	Object[][] executeTestPositive() {
		return new Object[][] { { StaticData.bookMap21Update, true },
				{ StaticData.bookMap22, false } };
	}

	@AfterClass
	public void tierDown() throws DaoException {
		updateBookCommand = null;
		BookListDaoImpl.getInstance().remove(StaticData.book21TestUpdated);
	}
}
