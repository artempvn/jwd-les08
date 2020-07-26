package by.artempvn.les08.controller.command.impl;

import java.util.HashMap;
import java.util.Map;
import by.artempvn.les08.controller.command.Command;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.exception.ServiceException;
import by.artempvn.les08.model.service.BookService;
import by.artempvn.les08.parser.BookMapParser;
import by.artempvn.les08.request.BookDataRequest;

public class UpdateBookCommand implements Command {
	private static final String KEY_VALUE = "Book was updated";
	private BookService bookService;

	@Override
	public Map<String, Object> execute(Map<String, String> params)
			throws ControllerException {
		BookMapParser bookMapParser = new BookMapParser();
		BookDataRequest bookData = bookMapParser.parseMapData(params);
		bookService = BookService.getInstance();
		boolean wasUpdated = true;
		try {
			bookService.update(bookData);
		} catch (ServiceException e) {
			wasUpdated = false;
		}
		Map<String, Object> output = new HashMap<>();
		output.put(KEY_VALUE, wasUpdated);
		return output;
	}
}
