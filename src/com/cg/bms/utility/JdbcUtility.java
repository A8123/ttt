package com.cg.bms.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.bms.exception.BMSException;

public class JdbcUtility {
	private static Connection connection = null;

	public static Connection getConnection() throws BMSException {

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(new File("resources/jdbc.properties")));

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,	password);
			} catch (ClassNotFoundException e) {
				throw new BMSException("unable to load the class");

			}

			catch (SQLException e) {
				throw new BMSException("connection was not established");

			}

		} catch (IOException e) {

			throw new BMSException(	"unable to read the data from the file, try again");
		}
		return connection;

	}
	
	public static void closeConnection() throws BMSException{
		
		try {
			connection.close();
		} catch (SQLException e) {
			throw new BMSException("unable to close the connection");
		}
	}
}
