package by.artempvn.les08.parser;

import java.util.Arrays;
import java.util.Map;

import by.artempvn.les08.controller.command.FieldType;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.request.BookDataRequest;
import by.artempvn.les08.validator.InputDataValidator;

public class BookMapParser {
	private static final String AUTHOR_SPLITTER = ", ";

	public BookDataRequest parseMapData(Map<String, String> params)
			throws ControllerException {
		String id = params.get(FieldType.ID.name());
		String title = params.get(FieldType.TITLE.name());
		String authors = params.get(FieldType.AUTHORS.name());
		String numberPages = params.get(FieldType.NUMBER_PAGES.name());
		String yearPublishing = params.get(FieldType.YEAR_PUBLISHING.name());
		InputDataValidator dataValidator = new InputDataValidator();
		if (!dataValidator.isLong(id) || !dataValidator.isNumber(numberPages)
				|| !dataValidator.isNumber(yearPublishing)) {
			throw new ControllerException("Invalid data input");
		}
		BookDataRequest bookData = new BookDataRequest();
		bookData.setId(Long.parseLong(id));
		bookData.setTitle(title);
		bookData.setAuthors(Arrays.asList(authors.split(AUTHOR_SPLITTER)));
		bookData.setNumberPages(Integer.parseInt(numberPages));
		bookData.setYearPublishing(Integer.parseInt(yearPublishing));
		return bookData;
	}
}
