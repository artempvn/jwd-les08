package by.artempvn.les08.validator;

import java.util.Map;
import java.util.Map.Entry;
import by.artempvn.les08.controller.command.FieldType;

public class InputDataValidator {
	private final static String ID_REGEX = "\\d{1,18}";
	private final static String YEAR_PAGES_REGEX = "\\d{1,4}";

	public boolean isNotNullMapValue(Map<String, String> map) {
		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey() == null || entry.getValue() == null
					|| entry.getValue().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean isCorrectFieldData(String field) {
		boolean isCorrect = false;
		for (FieldType type : FieldType.values()) {
			if (type.name().equalsIgnoreCase(field)) {
				isCorrect = true;
			}
		}
		return isCorrect;
	}

	public boolean isLong(String input) {
		return (input.matches(ID_REGEX));
	}

	public boolean isNumber(String input) {
		return (input.matches(YEAR_PAGES_REGEX));
	}
}
