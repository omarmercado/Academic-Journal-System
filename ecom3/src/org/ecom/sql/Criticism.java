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
	 * Model Class for critisim
	 * 
	 * 
	 */
public class Criticism {

		private boolean debug = false;
		private String ERROR = this.getClass().toString();
		private String method = "";

		/**
		 * Add critism to an article
		 * 
		 * @param submissionId
		 * @param content
		 * @param response
		 * @param userId
		 * @param reviewId
		 * @return
		 */
		public String setCriticism(int submissionId, String content,
				String response, int userId, int reviewId) {

			method = "setCriticism";

			if (debug) {
				System.out.println(method);
			}

			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "";
			int crtcismId = 0;
			String result = "Inserting new Criticism Failed";
			try {
				// conn = conection_.getConection();
				ConnPool ds = new ConnPool();
				conn = ds.getConnection();

				if (response.equals(null)) {
					response = "";
				} else if (content.equals(null)) {
					content = "";
				}

				query = "INSERT INTO crticism (description,content,response,reviewerId,accepted,reviewId) VALUES(?,?,?,?,?,?)";
				st = conn.prepareStatement(query.toString(),
						Statement.RETURN_GENERATED_KEYS);
				st.setString(1, "");
				st.setString(2, content);
				st.setString(3, response);
				st.setInt(4, userId);// reviewwer ID, cambiar reviewID - reviewerId
				st.setString(5, "");
				st.setInt(6, reviewId);
				st.executeUpdate();
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					crtcismId = rs.getInt(1);
				}

				if (crtcismId > 0) {
					st = null;
					query = "UPDATE review SET criticisimId  = ? WHERE submissionId = ? ";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, crtcismId);
					st.setInt(2, submissionId);
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
		 * Add response of a crtisim to database
		 * 
		 * @param crtcismId
		 * @param submissionId
		 * @param response
		 * @return
		 */
		public String respondCriticism(int crtcismId, int submissionId,
				String response) {

			method = "respondCriticism";

			if (debug) {
				System.out.println(method);
			}

			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "";

			int revisionId = 0;
			String result = "Inserting new Criticism Failed";
			try {
				// conn = conection_.getConection();

				ConnPool ds = new ConnPool();
				conn = ds.getConnection();

				if (response.equals(null)) {
					response = "";
				}

				query = "UPDATE crticism SET response = ? WHERE critismId =  ? ";
				st = conn.prepareStatement(query.toString());
				st.setString(1, response);
				st.setInt(2, crtcismId);
				int x = st.executeUpdate();

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
		 * Change the token value of a critisim
		 * 
		 * @param crtcismId
		 * @param submissionId
		 * @return
		 */
		public String acceptCriticism(int crtcismId, int submissionId) {

			method = "acceptCriticism";

			if (debug) {
				System.out.println(method);
			}

			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "";

			int revisionId = 0;
			String result = "Accept  Criticism Failed";
			try {
				// conn = conection_.getConection();

				ConnPool ds = new ConnPool();
				conn = ds.getConnection();

				query = "UPDATE crticism SET accepted = ? WHERE critismId =  ? ";
				st = conn.prepareStatement(query.toString());
				st.setString(1, "A");
				st.setInt(2, crtcismId);
				int x = st.executeUpdate();

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
		 * List all critisim filterd by userId
		 * 
		 * @param submissionId
		 * @param userId
		 * @return
		 */
		public ArrayList getCriticismbySubIdUserId(int submissionId, int userId) {

			method = "getCriticismbySubId";

			if (debug) {
				System.out.println(method);
			}
			ArrayList data = new ArrayList();
			ArrayList result = new ArrayList();
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "";
			int crtcismId = 0;

			try {
				// conn = conection_.getConection();
				ConnPool ds = new ConnPool();
				conn = ds.getConnection();

				query = "SELECT  critismId,content,response FROM crticism a,review b WHERE b.submissionId = ? and b.reviewerId = ? AND a.reviewerId = b.reviewerId AND a.reviewId = b.reviewId";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, submissionId);
				st.setInt(2, userId);
				rs = st.executeQuery();

				while (rs.next()) {
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					result.add(data.clone());
					data.clear();
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
		 * List all critism filtered by submission ID
		 * 
		 * @param submissionId
		 * @return
		 */
		public ArrayList getAllCriticismbySubId(int submissionId) {

			method = "getAllCriticismbySubId";

			if (debug) {
				System.out.println(method);
				System.out.println(submissionId);
			}
			ArrayList data = new ArrayList();
			ArrayList result = new ArrayList();
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "";
			int crtcismId = 0;

			try {
				// conn = conection_.getConection();// get version
				ConnPool ds = new ConnPool();
				conn = ds.getConnection();

				query = "SELECT  critismId,content,response FROM crticism a,review b WHERE b.submissionId = ? AND a.reviewId = b.reviewId";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, submissionId);

				rs = st.executeQuery();

				while (rs.next()) {
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					result.add(data.clone());
					data.clear();
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
	
}
