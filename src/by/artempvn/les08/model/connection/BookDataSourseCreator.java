package by.artempvn.les08.model.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class BookDataSourseCreator {

	private static final String DATABASE_PROPERTIES_PATH = 
			"properties\\library.properties";

	static MysqlDataSource createMysqlDataSourse() {
		MysqlDataSource dataSource = null;
		Properties properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(
					DATABASE_PROPERTIES_PATH);
			properties.load(inputStream);
			dataSource = new MysqlDataSource();
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUser(properties.getProperty("user"));
			dataSource.setPassword(properties.getProperty("password"));
			dataSource.setAutoReconnect(Boolean
					.parseBoolean(properties.getProperty("autoReconnect")));
			dataSource.setCharacterEncoding(
					properties.getProperty("characterEncoding"));
			dataSource.setUseUnicode(
					Boolean.parseBoolean(properties.getProperty("useUnicode")));
		} catch (IOException ex) {
			System.out.println("Failed to load properties");
		}
		return dataSource;
	}
}
