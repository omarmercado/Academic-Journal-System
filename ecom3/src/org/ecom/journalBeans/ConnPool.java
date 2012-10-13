/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnPool {

	public DataSource dataSource;

	public ConnPool() {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/jdbc/ecomDB");
		} catch (NamingException e) {
			System.out.println(e);
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
