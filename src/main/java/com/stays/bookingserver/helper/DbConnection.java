package com.stays.bookingserver.helper;

import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConnection {

	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;

	@Value("${db.username}")
	private String userName;

	@Value("${db.password}")
	private String password;

	private java.sql.Connection connection = null;

	public java.sql.Connection Connect() throws Exception {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		connection.setAutoCommit(false);

		// System.out.println("Connection ===== >>>>>" + connection);

		return connection;

	}

}
