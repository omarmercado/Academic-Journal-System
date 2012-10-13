package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.ecom.journalBeans.ConnPool;

/**
 * Article DAO This calss is using for connecting to database using JDBC
 * 
 */
public class Article {
	private String ERROR = this.getClass().toString();
	private boolean debug = false;
	private String method = "";

	/**
	 * Based on user will return all related articles
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getArticleUser(int userId) {
		String method = "getArticleUser";
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
			query = "SELECT DISTINCT a.articleId,a.title,a.publishDate, a.abstract, e.filepath  FROM article a, submission b, main_author c, affiliation d, file e WHERE"
					+ "  a.submissionId = b.submissionId AND b.mainAuthorId = c.authorId AND c.submissionId = a.submissionId "
					+ " AND c.mainAuthorId = d.mainAuthorId   AND b.submissionId = e.submissionId AND userId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// articleId
				data.add(rs.getString(2));// title
				data.add(rs.getString(3));// publishDate
				data.add(rs.getString(4));// abstract
				data.add(rs.getString(5));// URL
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

	/**
	 * Return all articles
	 * 
	 * @return
	 */
	public ArrayList getAllArticles() {
		String method = "getAllArticles";
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
			query = "SELECT a.articleId,a.issueId,a.publishDate,a.abstract,a.title,a.submissionId,c.firstName,b.mainAuthorId FROM "
					+ " article a,submission b,user c WHERE a.submissionId = b.submissionId AND mainAuthorId = userId ";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// articleId
				data.add(rs.getString(2));// issueId
				data.add(rs.getString(3));// publishDate
				data.add(rs.getString(4));// abstract
				data.add(rs.getString(5));// title
				data.add(rs.getString(6));// submissionId
				data.add(rs.getString(7));// user name
				data.add(rs.getString(8));// Main AuthorId
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

	/**
	 * Publish an article
	 * 
	 * @param submissionId
	 * @param title
	 * @param Abstract
	 * @return
	 */
	public String publish(int submissionId, String title, String Abstract) {
		method = "publish";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "Published Failed";
		int articleId = 0;
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ? WHERE submissionId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 9);
			st.setInt(2, submissionId);
			int y = st.executeUpdate();
			if (y > 0) {

				query = "SELECT  mainAuthorId  FROM  submission WHERE submissionId = ?";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, submissionId);
				rs = st.executeQuery();

				if (rs.next()) {
					int mainAuthorId = rs.getInt(1);// user ID
					query = "";

					query = "SELECT credit FROM credit WHERE userId = ?";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, mainAuthorId);
					rs = st.executeQuery();
					if (rs.next()) {

						int credit = rs.getInt(1);
						if (credit > 2) {

							query = "UPDATE submission SET statusId = ? WHERE submissionId = ?";
							st = conn.prepareStatement(query.toString());
							st.setInt(1, 10);
							st.setInt(2, submissionId);
							int y2 = st.executeUpdate();

							if (y2 > 0) {
								query = "";
								query = "INSERT INTO article (issueId, title, abstract, submissionId) VALUES(?,?,?,?)";
								st = conn.prepareStatement(query.toString(),
										Statement.RETURN_GENERATED_KEYS);
								st.setInt(1, 0);
								st.setString(2, title);
								st.setString(3, Abstract);
								st.setInt(4, submissionId);
								st.executeUpdate();
								rs = st.getGeneratedKeys();
								if (rs.next()) {

									articleId = rs.getInt(1);
									query = "";
									query = "UPDATE keyword SET articleId = ?  WHERE submissionId = ?";
									st = conn
											.prepareStatement(query.toString());
									st.setInt(1, articleId);
									st.setInt(2, submissionId);// User ID
									st.executeUpdate();

								}

								credit = credit - 3;
								query = "";
								query = "UPDATE credit SET credit = ?  WHERE userId = ?";
								st = conn.prepareStatement(query.toString());
								st.setInt(1, credit);
								st.setInt(2, mainAuthorId);// User ID
								st.executeUpdate();

								response = "Publish";

							}
						}
					} else {
						query = "INSERT INTO credit (userId,credit)values(?,?)";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, mainAuthorId);
						st.setInt(2, 0);// User ID
						int y3 = st.executeUpdate();

					}
				}
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
	 * By providing volume name and issue number will return an arraylist of
	 * article Indexes
	 * 
	 * @param volume
	 * @param issue
	 * @return
	 */
	public ArrayList getArticlesIndex(String volume, int issue) {
		String method = "getArticlesIndex";
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

			if (!volume.equals("") && issue > 0) {

				query = "SELECT a.articleId,a.issueId,a.publishDate,a.abstract,a.title,a.submissionId,c.firstName, b.mainAuthorId FROM "
						+ " article a,submission b,user c,volume d, issue e WHERE a.submissionId = b.submissionId AND  "
						+ "  a.issueId = e.issueId AND  d.volumeId = e.volumeId AND d.year = ? AND a.issueId = ? AND b.mainAuthorId = c.userId";
				st = conn.prepareStatement(query.toString());
				st.setString(1, volume);
				st.setInt(2, issue);
				rs = st.executeQuery();

				while (rs.next()) {
					data.add(rs.getString(1));// articleId
					data.add(rs.getString(2));// issueId
					data.add(rs.getString(3));// publishDate
					data.add(rs.getString(4));// abstract
					data.add(rs.getString(5));// title
					data.add(rs.getString(6));// submissionId
					data.add(rs.getString(7));// user name
					data.add(rs.getString(8));// main author id
					response.add(data.clone());
					data.clear();
				}

			} else {

				query = "SELECT a.articleId,a.issueId,a.publishDate,a.abstract,a.title,a.submissionId,c.firstName, b.mainAuthorId FROM "
						+ " article a,submission b,user c,volume d, issue e WHERE a.submissionId = b.submissionId AND  "
						+ "  a.issueId = e.issueId AND  d.volumeId = e.volumeId AND d.year = ? AND b.mainAuthorId = c.userId";
				st = conn.prepareStatement(query.toString());
				st.setString(1, volume);
				rs = st.executeQuery();

				while (rs.next()) {
					data.add(rs.getString(1));// articleId
					data.add(rs.getString(2));// issueId
					data.add(rs.getString(3));// publishDate
					data.add(rs.getString(4));// abstract
					data.add(rs.getString(5));// title
					data.add(rs.getString(6));// submissionId
					data.add(rs.getString(7));// user name
					data.add(rs.getString(8));// main author id
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

	/**
	 * Return all Volumes
	 * 
	 * @return
	 */
	public ArrayList getVolumes() {
		String method = "getVolumes";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList data = new ArrayList();

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT year FROM volume";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// year
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
		return data;
	}

	/**
	 * Retrun a related issue of an volume
	 * 
	 * @param volume
	 * @return
	 */
	public ArrayList getIssue(String volume) {
		String method = "getIssiue";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList data = new ArrayList();

		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT issueId FROM issue WHERE year = ?";
			st = conn.prepareStatement(query.toString());
			st.setString(1, volume);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// year
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
		return data;
	}

	/**
	 * Retrun detailed information of an article
	 * 
	 * @param ArticleId
	 * @return
	 */
	public ArrayList getArticleInfo(String ArticleId) {
		String method = "getArticleInfo";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList data = new ArrayList();

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT a.issueId,a.title,a.abstract,c.email FROM article a, submission b, user c  WHERE "
					+ " a.submissionId = b.submissionId AND b.mainAuthorId = c.userId AND a.articleId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, Integer.valueOf(ArticleId));
			rs = st.executeQuery();

			if (rs.next()) {
				data.add(rs.getString(1));// issueId
				data.add(rs.getString(2));// title
				data.add(rs.getString(3));// abstract
				data.add(rs.getString(4));// Author Email
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
		return data;
	}
}
