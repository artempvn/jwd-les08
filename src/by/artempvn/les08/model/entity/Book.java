package by.artempvn.les08.model.entity;

import java.util.Collections;
import java.util.List;

public class Book {
	private long id = -1;
	private String title;
	private List<String> authors;
	private int numberPages;
	private int yearPublishing;

	public Book(String title, List<String> authors, int numberPages,
			int yearPublishing) {
		this.title = title;
		this.authors = authors;
		this.numberPages = numberPages;
		this.yearPublishing = yearPublishing;
	}

	public Book(long id, String title, List<String> authors, int numberPages,
			int yearPublishing) {
		this(title, authors, numberPages, yearPublishing);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getAuthorsReadOnly() {
		return Collections.unmodifiableList(authors);
	}

	public int getNumberPages() {
		return numberPages;
	}

	public int getYearPublishing() {
		return yearPublishing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + numberPages;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + yearPublishing;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null) {
				return false;
			}
		} else if (!authors.equals(other.authors)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (numberPages != other.numberPages) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (yearPublishing != other.yearPublishing) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", title=").append(title)
				.append(", authors=").append(authors).append(", numberPages=")
				.append(numberPages).append(", yearPublishing=")
				.append(yearPublishing).append("]");
		return builder.toString();
	}
}
