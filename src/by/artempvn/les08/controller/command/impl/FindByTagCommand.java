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

public class FindByTagCommand implements Command {
	private static final String KEY_VALUE = "Found books";
	private static final String ERROR_VALUE = "Invalid value of sorting/searching parameter";
	private static final String ERROR_VALUE_SQL = "SQL error";
	private static final String SEARCH_PARAM = "tag";
	private static final String VALUE_PARAM = "value";
	private static final String SORT_PARAM = "sort";
	private BookService bookService;

	@Override
	public Map<String, Object> execute(Map<String, String> params) {
		bookService = BookService.getInstance();
		InputDataValidator dataValidator = new InputDataValidator();
		List<Book> foundList = Collections.emptyList();
		Map<String, Object> output = new HashMap<>();
		try {
			if (dataValidator.isCorrectFieldData(params.get(SEARCH_PARAM))
					&& dataValidator
							.isCorrectFieldData(params.get(SORT_PARAM))) {
				FieldType searchType = FieldType
						.valueOf(params.get(SEARCH_PARAM).toUpperCase());
				FieldType sortType = FieldType
						.valueOf(params.get(SORT_PARAM).toUpperCase());
				String value = params.get(VALUE_PARAM);
				switch (searchType) {
				case ID:
					if (dataValidator.isLong(value)) {
						foundList = bookService.findById(Long.parseLong(value));
					}
					break;
				case TITLE:
					foundList = bookService.findByTitle(value, sortType);
					break;
				case AUTHORS:
					foundList = bookService.findByAuthor(value, sortType);
					break;
				case NUMBER_PAGES:
					if (dataValidator.isNumber(value)) {
						foundList = bookService.findByNumberPages(
								Integer.parseInt(value), sortType);
					}
					break;
				case YEAR_PUBLISHING:
					if (dataValidator.isNumber(value)) {
						foundList = bookService.findByYearPublishing(
								Integer.parseInt(value), sortType);
					}
					break;
				}
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
