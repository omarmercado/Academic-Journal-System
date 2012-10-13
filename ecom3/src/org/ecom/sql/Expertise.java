/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.ecom.journalBeans.ConnPool;

/**
 * 
 * Expertise DAO class
 * 
 */
public class Expertise {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * List out all expertise levels
	 * 
	 * @return
	 */
	public ArrayList getExpertise() {

		method = "getExpertise";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {
			// conn = conection_.getConection();

			query = "SELECT  expertieId , expertiseLevel FROM expertise";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// Expertise Id
				data.add(rs.getString(2));// Expertise Level
				response.add(data.clone());
				data.clear();
			}
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		} finally {
			try {
				st.close();
			} catch (Exception e) {
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
		return response;
	}
}
