/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.ecom.journalBeans.ConnPool;

/**
 * DEPRICATED TEST CLASS
 * 
 * 
 */
public class testCon {

	public static void main(String[] args) {

	}

	public ArrayList getVolumes() {
		String method = "getVolumes";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList data = new ArrayList();
		BasicDataSource b = null;

		try {
			// conn = conection.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			b = (BasicDataSource) ds.dataSource;

			System.out.println("11111111111111111111");
			// conn = ConnPool.getConnection();
			System.out.println(conn);

			System.out.println(b.getInitialSize());
			System.out.println(b.getMaxActive());
			System.out.println(b.getMaxIdle());
			System.out.println(b.getNumActive());
			System.out.println(b.getNumIdle());

			query = "SELECT year FROM volume";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// year

				System.out.println(b.getNumActive());
				System.out.println(b.getNumIdle());

			}

		} catch (Exception ex) {
			System.err.println(" - " + method + " - " + ex);
		} finally {
			try {
				st.close();
			} catch (Exception e) {
			}
			rs = null;
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}

		System.out.println(data);
		System.out.println(b.getNumActive());
		System.out.println(b.getNumIdle());
		return data;
	}

}
