package by.artempvn.les08.controller.command.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.artempvn.les08.controller.command.Command;
import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.ServiceException;
import by.artempvn.les08.model.entity.Book;
import by.artempvn.les08.model.service.BookService;
import by.artempvn.les08.validator.InputDataValidator;

public class TakeAllBooksCommand implements Command {
	private static final String KEY_VALUE = "Found books";
	private static final String ERROR_VALUE = "Invalid value of sorting parameter";
	private static final String ERROR_VALUE_SQL = "SQL error";
	private static final String SORT_PARAM = "sort";
	private BookService bookService;

	@Override
	public Map<String, Object> execute(Map<String, String> params) {
		bookService = BookService.getInstance();
		InputDataValidator dataValidator = new InputDataValidator();
		List<Book> foundList = Collections.emptyList();
		Map<String, Object> output = new HashMap<>();
		try {
			if (dataValidator.isCorrectFieldData(params.get(SORT_PARAM))) {
				FieldType sortType = FieldType
						.valueOf(params.get(SORT_PARAM).toUpperCase());
				foundList = bookService.takeAllBooks(sortType);
			} else {
				output.put(ERROR_VALUE, true);
			}
		} catch (ServiceException ex) {
			output.put(ERROR_VALUE_SQL, true);
		}
		output.put(KEY_VALUE, foundList);
		return output;
	}
}
