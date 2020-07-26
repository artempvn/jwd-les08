package by.artempvn.les08.validator;

import java.time.Year;
import java.util.List;

public class BookValidator {

	private static final int MIN_YEAR = 1920;
	private static final int MAX_NUMBER_PAGES = 10_000;
	private static final int MIN_NUMBER_PAGES = 30;
	private static final int UNSET_ID = -1;
	private static final String TITLE_REGEX = "[\\p{L}\\d]{1}.*";
	private static final String AUTHOR_REGEX = "\\p{L}+([\\s-']\\p{L}+){0,4}";

	public boolean isIdCorrect(long id) {
		return (id >= UNSET_ID);
	}

	public boolean isTitleCorrect(String title) {
		return (title.matches(TITLE_REGEX));
	}

	public boolean isAuthorCorrect(String author) {
		return (author.matches(AUTHOR_REGEX));
	}

	public boolean isAuthorsCorrect(List<String> authors) {
		boolean isCorrect = true;
		if (authors.size() > 0) {
			for (String author : authors) {
				if (!author.matches(AUTHOR_REGEX)) {
					isCorrect = false;
				}
			}
		} else {
			isCorrect = false;
		}
		return isCorrect;
	}

	public boolean isNumberPagesCorrect(int numberPages) {
		return (numberPages >= MIN_NUMBER_PAGES
				&& numberPages <= MAX_NUMBER_PAGES);
	}

	public boolean isYearPublishingCorrect(int yearPublishing) {
		int currentYear = Year.now().getValue();
		return (yearPublishing >= MIN_YEAR && yearPublishing <= currentYear);
	}
}
