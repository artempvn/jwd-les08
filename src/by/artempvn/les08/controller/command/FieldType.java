package by.artempvn.les08.controller.command;

public enum FieldType {
	ID("id"), TITLE("title"), AUTHORS("authors"),
	NUMBER_PAGES("number_of_pages"), YEAR_PUBLISHING("year_of_publishing");

	private static final String SQL_ORDER = "ORDER BY ";
	private String sqlSort;

	FieldType(String columnName) {
		sqlSort = SQL_ORDER + columnName;
	}

	public String getSqlSort() {
		return sqlSort;
	}
}
