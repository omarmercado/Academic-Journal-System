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
 * Score DAO class
 * 
 * 
 */
public class Score {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Return all scores
	 * 
	 * @return
	 */
	public ArrayList getScores() {

		method = "getScores";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT scoreName, scoreLevel FROM score";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// SCORE NAME
				data.add(rs.getString(2));// SCORE LEVEL
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
