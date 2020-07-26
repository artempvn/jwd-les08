package test.artempvn.les08.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.DataProvider;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.request.BookDataRequest;

public class StaticData {

	public static final Book book21Test = new Book(21, "title",
			Arrays.asList("author one"), 310, 1998);
	public static final Book book21TestUpdated = new Book(21, "newTitle",
			Arrays.asList("author one"), 310, 1998);
	public static final Book book20 = new Book(20,
			"Java. Методы программирования",
			Arrays.asList("Игорь Блинов", "Валерий Романчик"), 896, 2013);
	public static final Book book10 = new Book(10, "Doomed",
			Arrays.asList("Chuck Palahniuk"), 336, 2013);
	public static final Book book1TestNegative = new Book(1, "title",
			Arrays.asList("author one"), 320, 2008);
	public static final Book book22TestNegative = new Book(22, "title",
			Arrays.asList("author one"), 320, 2008);
	public static final BookDataRequest book21DataTest = new BookDataRequest(21,
			"title", Arrays.asList("author one"), 310, 1998);
	public static final BookDataRequest book21DataTestUpdated = new BookDataRequest(
			21, "newTitle", Arrays.asList("author one"), 310, 1998);
	public static final BookDataRequest book1DataTestNegative = new BookDataRequest(
			1, "title", Arrays.asList("author one"), 320, 2008);
	public static final BookDataRequest book22DataTestNegative = new BookDataRequest(
			22, "title", Arrays.asList("author one"), 320, 2008);
	public static final BookDataRequest bookDataTestNegative = new BookDataRequest(
			21, "title", Arrays.asList("author 1"), -5, 2099);
	public static final Map<String, String> bookMap1 = new HashMap<>();
	public static final Map<String, String> mapTestFalse1 = new HashMap<>();
	public static final Map<String, String> mapTestFalse2 = new HashMap<>();
	public static final Map<String, String> mapTestFalse3 = new HashMap<>();
	public static final Map<String, String> bookMap21 = new HashMap<>();
	public static final Map<String, String> bookMap21Update = new HashMap<>();
	public static final Map<String, String> bookMap22 = new HashMap<>();

	static {
		bookMap1.put("ID", "1");
		bookMap1.put("TITLE", "title");
		bookMap1.put("AUTHORS", "author one");
		bookMap1.put("NUMBER_PAGES", "310");
		bookMap1.put("YEAR_PUBLISHING", "1998");
		mapTestFalse1.put("ID", "Word");
		mapTestFalse1.put("TITLE", "title");
		mapTestFalse1.put("AUTHORS", "author one/author two");
		mapTestFalse1.put("NUMBER_PAGES", "320");
		mapTestFalse1.put("YEAR_PUBLISHING", "1998");
		mapTestFalse2.put("ID", "31");
		mapTestFalse2.put("TITLE", "title");
		mapTestFalse2.put("AUTHORS", "author one/author two");
		mapTestFalse2.put("NUMBER_PAGES", "word");
		mapTestFalse2.put("YEAR_PUBLISHING", "1998");
		mapTestFalse3.put("ID", "31");
		mapTestFalse3.put("TITLE", "title");
		mapTestFalse3.put("AUTHORS", "author one/author two");
		mapTestFalse3.put("NUMBER_PAGES", "320");
		mapTestFalse3.put("YEAR_PUBLISHING", "word");
		bookMap21.put("ID", "21");
		bookMap21.put("TITLE", "title");
		bookMap21.put("AUTHORS", "author one");
		bookMap21.put("NUMBER_PAGES", "310");
		bookMap21.put("YEAR_PUBLISHING", "1998");
		bookMap21Update.put("ID", "21");
		bookMap21Update.put("TITLE", "newTitle");
		bookMap21Update.put("AUTHORS", "author one");
		bookMap21Update.put("NUMBER_PAGES", "310");
		bookMap21Update.put("YEAR_PUBLISHING", "1998");
		bookMap22.put("ID", "22");
		bookMap22.put("TITLE", "title");
		bookMap22.put("AUTHORS", "author one");
		bookMap22.put("NUMBER_PAGES", "310");
		bookMap22.put("YEAR_PUBLISHING", "1998");
	}

	@DataProvider
	public Object[][] findByIdTest() {
		return new Object[][] { { 20, Arrays.asList(StaticData.book20) },
				{ 22, Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByTitleTest() {
		return new Object[][] { { "Методы", Arrays.asList(StaticData.book20) },
				{ "unfound", Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByAuthorTest() {
		return new Object[][] { { "Игорь", Arrays.asList(StaticData.book20) },
				{ "unfound", Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByNumberPagesTest() {
		return new Object[][] { { 896, Arrays.asList(StaticData.book20) },
				{ 35, Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByYearPublishingTest() {
		return new Object[][] {
				{ 2013, Arrays.asList(StaticData.book10, StaticData.book20) },
				{ 1995, Arrays.asList() } };
	}
}
