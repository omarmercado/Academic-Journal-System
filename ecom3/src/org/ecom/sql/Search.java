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
	 * Search Model Class Representing Searching model for journal
	 * 
	 * 
	 */
	public class Search {
		
	    private boolean debug = false;
		private String ERROR = this.getClass().toString();
		private String method = "";

		/**
		 * based option search can be done options are : "title" , "keywords" ,
		 * "authors" , "date"
		 * 
		 * @param option
		 * @param search
		 * @return
		 */
		public ArrayList execSearch(String option, ArrayList search) {

			method = "execSearch";

			if (debug) {
				System.out.println(method);
				System.out.println(option);
				System.out.println(search);
			}

			Connection conn = null;
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			PreparedStatement st = null;
			ArrayList response = new ArrayList();
			ArrayList data = new ArrayList();
			ResultSet rs = null;
			ResultSet rs2 = null;
			String query = null;
			try {

				if (option.equals("title")) {
					System.out.println("1");
					query = " SELECT  a.articleId, a.title,a.abstract,c.firstName, c.lastName ,b.mainAuthorId  FROM article a, submission b, user c WHERE a.submissionId = b.submissionId "
							+ " AND b.mainAuthorId = c.userId AND title like  ? ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, "%" + search.get(0).toString() + "%");

					rs = st.executeQuery();

					while (rs.next()) {
						data.add(rs.getInt(1));// Article ID
						data.add(rs.getString(2));// Title
						data.add(rs.getString(3));// Abstract
						data.add(rs.getString(4));// First Name
						data.add(rs.getString(5));// Last Name
						data.add(rs.getString(6));// Main Author Id
						response.add(data.clone());
						data.clear();
					}
				}

				if (option.equals("keywords")) {
					System.out.println("2");
					query = "SELECT  b.articleId,b.title,b.abstract, d.firstName, d.lastName, c.mainAuthorId  FROM  keyword a, article b, submission c, user d  WHERE  a.keyword = ?  AND a.articleId = b.articleId "
							+ "  AND b.submissionId = c.submissionId AND c.mainAuthorId = d.userId    ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, "%" + search.get(0).toString() + "%");

					rs2 = st.executeQuery();

					while (rs2.next()) {
						data.add(rs.getInt(1));// Article ID
						data.add(rs.getString(2));// Title
						data.add(rs.getString(3));// Abstract
						data.add(rs.getString(4));// First Name
						data.add(rs.getString(5));// Last Name
						data.add(rs.getString(6));// Main Author Id
						response.add(data.clone());
						data.clear();
					}

				}

				if (option.equals("authors")) {
					System.out.println("3");

					query = "SELECT f.articleId,f.title,f.abstract,a.firstName, a.lastName, d.mainAuthorId from user a, affiliation b, main_author c, submission d, revision e, article f  "
							+ " WHERE (a.firstName like ? OR a.lastName like ?) AND a.userId = b.userId  AND b.mainAuthorId = c.authorId AND c.submissionId = d.submissionId AND  "
							+ " d.revisionId = e.revisionId AND f.submissionId = d.submissionId ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, "%" + search.get(0).toString() + "%");
					st.setString(2, "%" + search.get(0).toString() + "%");

					rs = st.executeQuery();

					while (rs.next()) {
						data.add(rs.getInt(1));// Article ID
						data.add(rs.getString(2));// Title
						data.add(rs.getString(3));// Abstract
						data.add(rs.getString(4));// First Name
						data.add(rs.getString(5));// Last Name
						data.add(rs.getString(6));// Main Author Id
						response.add(data.clone());
						data.clear();
					}

					query = "SELECT d.articleId,d.title,d.abstract,a.firstName, a.lastName, b.mainAuthorId FROM user a, submission b,revision c, article d WHERE (a.firstName like ? OR a.lastName like ?) AND "
							+ " a.userId=b.mainAuthorId AND b.revisionId = c.revisionId  AND d.submissionId = b.submissionId ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, "%" + search.get(0).toString() + "%");
					st.setString(2, "%" + search.get(0).toString() + "%");

					rs = st.executeQuery();

					while (rs.next()) {
						data.add(rs.getInt(1));// Article ID
						data.add(rs.getString(2));// Title
						data.add(rs.getString(3));// Abstract
						data.add(rs.getString(4));// First Name
						data.add(rs.getString(5));// Last Name
						data.add(rs.getString(6));// Main Author Id
						response.add(data.clone());
						data.clear();
					}
				}

				if (option.equals("date")) {
					System.out.println("3");

					query = "SELECT a.articleId,a.title,a.abstract,c.firstName, c.lastName,b.mainAuthorId FROM article a, submission b, user c WHERE a.publishDate BETWEEN CONCAT(?,' 00:00:00') "
							+ "AND CONCAT(?,' 00:00:00') AND b.submissionId = a.submissionId AND b.mainAuthorId = c.userId ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, search.get(0).toString());
					st.setString(2, search.get(1).toString());

					rs = st.executeQuery();

					while (rs.next()) {
						data.add(rs.getInt(1));// Article ID
						data.add(rs.getString(2));// Title
						data.add(rs.getString(3));// Abstract
						data.add(rs.getString(4));// First Name
						data.add(rs.getString(5));// Last Name
						data.add(rs.getString(6));// Main Author Id
						response.add(data.clone());
						data.clear();
					}

				}

			} catch (Exception ex) {
				System.err.println(ERROR + " - " + method + " - " + ex);
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
			return response;
		}
	

	

}
