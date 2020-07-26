package test.artempvn.les08.validator;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les08.validator.InputDataValidator;

public class InputDataValidatorTest {
	InputDataValidator dataValidator;
	Map<String, String> mapTestTrue;
	Map<String, String> mapTestFalse1;
	Map<String, String> mapTestFalse2;
	Map<String, String> mapTestFalse3;

	@BeforeClass
	public void setUp() {
		dataValidator = new InputDataValidator();
		mapTestTrue = new HashMap<>();
		mapTestTrue.put("key", "value");
		mapTestFalse1 = new HashMap<>();
		mapTestFalse1.put(null, "value");
		mapTestFalse2 = new HashMap<>();
		mapTestFalse2.put("key", "");
		mapTestFalse3 = new HashMap<>();
		mapTestFalse3.put("key", null);
	}

	@Test(dataProvider = "isNotNullMapValueTest")
	public void isNotNullMapValueTest(Map<String, String> map,
			boolean expected) {
		boolean actual = dataValidator.isNotNullMapValue(map);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isNotNullMapValueTest() {
		return new Object[][] { { mapTestTrue, true }, { mapTestFalse1, false },
				{ mapTestFalse2, false }, { mapTestFalse3, false } };
	}

	@Test(dataProvider = "isCorrectFieldDataTest")
	public void isCorrectFieldDataTest(String field, boolean expected) {
		boolean actual = dataValidator.isCorrectFieldData(field);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isCorrectFieldDataTest() {
		return new Object[][] { { "id", true }, { "Title", true },
				{ "notField", false } };
	}

	@Test(dataProvider = "isLongTest")
	public void isLongTest(String data, boolean expected) {
		boolean actual = dataValidator.isLong(data);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isLongTest() {
		return new Object[][] { { "31", true }, { "notNumber", false } };
	}

	@Test(dataProvider = "isNumberTest")
	public void isNumberTest(String data, boolean expected) {
		boolean actual = dataValidator.isNumber(data);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] isNumberTest() {
		return new Object[][] { { "2000", true }, { "31", true },
				{ "NaN", false } };
	}

	@AfterClass
	public void tierDown() {
		dataValidator = null;
		mapTestTrue = null;
		mapTestFalse1 = null;
		mapTestFalse2 = null;
		mapTestFalse3 = null;
	}
}
