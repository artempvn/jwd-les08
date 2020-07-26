package by.artempvn.les08.model.connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class LibraryConnector {

	private static LibraryConnector libraryConnector;
	private MysqlDataSource dataSource;

	private LibraryConnector() {
		dataSource = BookDataSourseCreator.createMysqlDataSourse();
	}

	public static LibraryConnector getInstance() {
		if (libraryConnector == null) {
			libraryConnector = new LibraryConnector();
		}
		return libraryConnector;
	}

	public MysqlDataSource getDataSource() {
		return dataSource;
	}
}