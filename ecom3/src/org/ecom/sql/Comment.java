/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.ecom.journalBeans.ConnPool;

/**
 * Comment DAO Class
 * 
 * 
 */
public class Comment {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Add comment to a submission (a possible article)
	 * 
	 * @param coment
	 * @param submissionId
	 * @param response
	 * @param userId
	 * @param roleId
	 * @param title
	 * @return
	 */
	public String setComent(String coment, int submissionId, String response,
			int userId, int roleId, String title) {

		method = "setComent";

		if (debug) {
			System.out.println(method);
			System.out.println(coment);
			System.out.println(submissionId);
			System.out.println(response);
			System.out.println(userId);
			System.out.println(roleId);
			System.out.println(title);

		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int cmTypeId = 0;
		String result = "Inserting new coment Failed";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			if (response == null) {
				response = "";
			} else if (coment == null) {
				coment = "";
			}

			if (roleId == 2) {
				roleId = 3;
			} else if (roleId == 2) {
				roleId = 2;
			}

			query = "INSERT INTO comment_type (roleId,title) VALUES(?,?)";
			st = conn.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, roleId);
			st.setString(2, title);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				cmTypeId = rs.getInt(1);
			}

			if (cmTypeId > 0) {
				query = "INSERT INTO comment (comment,submissionId,cmTypeId,response,userId) VALUES(?,?,?,?,?)";
				st = conn.prepareStatement(query.toString(),
						Statement.RETURN_GENERATED_KEYS);
				st.setString(1, coment);
				st.setInt(2, submissionId);
				st.setInt(3, cmTypeId);
				st.setString(4, response);
				st.setInt(5, userId);
				int x = st.executeUpdate();
				if (x > 0) {
					result = "OK";
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
		return result;
	}

	/**
	 * Return an arraylist of comments related to a submission
	 * 
	 * @param submissionId
	 * @return
	 */
	public ArrayList getComments(int submissionId) {

		method = "getComments";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;

		try {

			query = "SELECT commentId,userName,roleId,comment,response,title  FROM submission a,comment b,comment_type c, user d  WHERE "
					+ " a.submissionId = b.submissionId AND a.submissionId = ? AND b.cmTypeId = c.cmTypeId and b.userId = d.userId ";

			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);// Submission Id of the required comments
										// and responses
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString("commentId"));
				data.add(rs.getString("userName"));
				data.add(rs.getString("roleId"));
				data.add(rs.getString("comment"));
				data.add(rs.getString("response"));
				data.add(rs.getString("title"));
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

	/**
	 * Return a comment related to a submission and specific user
	 * 
	 * @param submissionId
	 * @param userId
	 * @return
	 */
	public ArrayList getCommentsByUser(int submissionId, int userId) {

		method = "getComments";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		Connection conn = null;
		// conn = conection_.getConection();

		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;

		try {

			query = "SELECT commentId,userName,roleId,comment,response,title  FROM submission a,comment b,comment_type c, user d  WHERE "
					+ " a.submissionId = b.submissionId AND a.submissionId = ? AND b.cmTypeId = c.cmTypeId and b.userId = d.userId AND b.userId = ? ";

			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);// Submission Id of the required comments
										// and responses
			st.setInt(2, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString("commentId"));
				data.add(rs.getString("userName"));
				data.add(rs.getString("roleId"));
				data.add(rs.getString("comment"));
				data.add(rs.getString("response"));
				data.add(rs.getString("title"));
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

	/**
	 * Return Response of a comment
	 * 
	 * @param commentId
	 * @param response
	 * @return
	 */
	public String responseCom(int commentId, String response) {

		method = "responseCom";

		if (debug) {
			System.out.println(method);
			System.out.println(commentId);
			System.out.println(response);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		String result = "Respond to responseCom Failed";
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE comment SET response  = ? WHERE commentId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setString(1, response);
			st.setInt(2, commentId);
			int x = st.executeUpdate();

			if (x > 0) {
				result = "OK";
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
		return result;
	}

	/**
	 * Retrun list of all comments to editor
	 * 
	 * @return
	 */
	public ArrayList getListComEditor() {

		method = "getListComEditor";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		Connection conn = null;
		// conn = conection_.getConection();

		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;

		try {

			query = "SELECT a.submissionId,c.title, a.mainAuthorId, c.abstract FROM submission a, comment b, revision c  WHERE"
					+ " a.submissionId=b.submissionId  AND a.revisionId = c.revisionId AND b.response = ?";

			st = conn.prepareStatement(query.toString());
			st.setString(1, "");
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
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
