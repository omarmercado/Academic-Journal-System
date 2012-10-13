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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ecom.journalBeans.ArticleModel;
import org.ecom.journalBeans.ConnPool;
import org.ecom.journalBeans.emailSender;

/**
 * 
 * Issue DAO class
 * 
 */
public class Issue {

	private String ERROR = this.getClass().toString();
	private boolean debug = false;
	private String method = "";

	/**
	 * Create a new issue
	 * 
	 * @return
	 */
	public String newIssue() {
		String method = "newIssue";
		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;

		ResultSet rs5 = null;
		ResultSet rs6 = null;

		String query = "";
		try {

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			Date date = new Date();
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			String current_year = simpleDateformat.format(date).toString()
					.trim();

			int volumeId = 0;

			query = "SELECT  articleId FROM article WHERE issueId=?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 0);
			rs4 = st.executeQuery();
			if (rs4.next()) {

				query = "SELECT  volumeId FROM volume WHERE year=?";
				st = conn.prepareStatement(query.toString());
				st.setString(1, current_year);
				rs = st.executeQuery();
				if (rs.next()) {
					volumeId = rs.getInt(1);

					query = "INSERT INTO issue (volumeId,year , issueNumber , active) VALUES(?,?,?,?)";
					st = conn.prepareStatement(query.toString(),
							Statement.RETURN_GENERATED_KEYS);
					st.setInt(1, volumeId);
					st.setString(2, current_year);
					st.setString(3, "");
					st.setInt(4, 0);
					st.executeUpdate();
					rs2 = st.getGeneratedKeys();
					if (rs2.next()) {
						int issueId = rs2.getInt(1);

						query = "SELECT  articleId FROM article WHERE issueId=?";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, 0);
						rs3 = st.executeQuery();

						while (rs3.next()) {
							query = "UPDATE article SET issueId = ? WHERE articleId = ?";
							st = conn.prepareStatement(query.toString());
							st.setInt(1, issueId);
							st.setInt(2, rs3.getInt(1));
							st.executeUpdate();

						}

						query = "SELECT  email FROM subscription WHERE issue <> ?";
						st = conn.prepareStatement(query.toString());
						st.setString(1, "");
						rs5 = st.executeQuery();
						while (rs5.next()) {
							String content = "There is a new Issue publish  ";
							emailSender e = new emailSender();
							e.sendEmail(rs5.getString(1),
									"Subscription Remainder", content);

						}

						query = "SELECT c.email FROM article a, keyword b, subscription c WHERE a.articleId = b.articleId AND b.keyword = c.keyword";
						st = conn.prepareStatement(query.toString());
						rs6 = st.executeQuery();
						while (rs6.next()) {

							String content = "There is a new Issue publish  and it contains keywords you have susbcribe to  ";
							emailSender e = new emailSender();
							e.sendEmail(rs6.getString(1),
									"Submission Remainder", content);
						}
					}

				} else {

					query = "INSERT INTO volume (journalId, year , description , volumeNumber , active) VALUES(?,?,?,?,?)";
					st = conn.prepareStatement(query.toString(),
							Statement.RETURN_GENERATED_KEYS);
					st.setInt(1, 0);
					st.setString(2, current_year);
					st.setString(3, "");
					st.setString(4, "");
					st.setInt(5, 0);
					st.executeUpdate();
					rs = st.getGeneratedKeys();
					if (rs.next()) {
						volumeId = rs.getInt(1);

						query = "INSERT INTO issue (volumeId,year , issueNumber , active) VALUES(?,?,?,?)";
						st = conn.prepareStatement(query.toString(),
								Statement.RETURN_GENERATED_KEYS);
						st.setInt(1, volumeId);
						st.setString(2, current_year);
						st.setString(3, "");
						st.setInt(4, 0);
						st.executeUpdate();
						rs2 = st.getGeneratedKeys();
						if (rs2.next()) {
							int issueId = rs2.getInt(1);

							Issue is = new Issue();
							is.setSubscriptionsByIssue(issueId);

							query = "SELECT  articleId FROM article WHERE issueId=?";
							st = conn.prepareStatement(query.toString());
							st.setInt(1, 0);
							rs3 = st.executeQuery();

							while (rs3.next()) {
								query = "UPDATE article SET issueId = ? WHERE articleId = ?";
								st = conn.prepareStatement(query.toString());
								st.setInt(1, issueId);
								st.setInt(2, rs3.getInt(1));
								st.executeUpdate();
								is.setSubscriptionsByKeywords(rs3.getInt(1));

							}

							query = "SELECT  email FROM subscription WHERE issue <> ?";
							st = conn.prepareStatement(query.toString());
							st.setString(1, "");
							rs5 = st.executeQuery();
							while (rs5.next()) {
								String content = "There is a new Issue publish  ";
								emailSender e = new emailSender();
								e.sendEmail(rs5.getString(1),
										"Subscription Remainder", content);

							}

							query = "SELECT c.email FROM article a, keyword b, subscription c WHERE a.articleId = b.articleId AND b.keyword = c.keyword";
							st = conn.prepareStatement(query.toString());
							rs6 = st.executeQuery();
							while (rs6.next()) {

								String content = "There is a new Issue publish  and it contains keywords you have susbcribe to  ";
								emailSender e = new emailSender();
								e.sendEmail(rs6.getString(1),
										"Submission Remainder", content);
							}
						}
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
			rs = null;
			rs2 = null;
			rs3 = null;
			rs4 = null;
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
		return String.valueOf(response);
	}

	/**
	 * Add a read as subsriber
	 * 
	 * @param articleId
	 * @return
	 */
	public String setSubscriptionsByKeywords(int articleId) {

		method = "setSubscriptionsByKeywords";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		Connection conn = null;
		// conn = conection_.getConection();

		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String query = "";
		int result = 0;

		try {

			query = " SELECT keyword FROM submission a, keyword b, article c WHERE "
					+ "a.submissionId = b.submissionId AND c.submissionId = a.submissionId AND c.articleId = ? ";

			st = conn.prepareStatement(query.toString());
			st.setInt(1, articleId);
			rs = st.executeQuery();

			while (rs.next()) {

				query = "SELECT email FROM subscription WHERE  keyword = ?";
				st = conn.prepareStatement(query.toString());
				st.setString(1, rs.getString(1));
				rs2 = st.executeQuery();

				while (rs2.next()) {
					ArticleModel art = new ArticleModel();
					ArrayList artInfo = new ArrayList();

					artInfo = art.getArticleInfo(String.valueOf(articleId));

					String content = "You subscribe to the Keyword "
							+ rs.getString(1) + " The following article "
							+ artInfo.get(1) + "  "
							+ "that is part of the issue  " + artInfo.get(0)
							+ "  contains that keyword";
					emailSender e = new emailSender();
					e.sendEmail(rs2.getString(1), "Subscription alert", content);
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
	 * Add a user as subscriber to an issue
	 * 
	 * @param issueId
	 * @return
	 */
	public String setSubscriptionsByIssue(int issueId) {

		method = "setSubscriptionsByIssue";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		Connection conn = null;
		// conn = conection_.getConection();

		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";

		try {

			query = " SELECT email FROM subscription WHERE  issue <> ? ";

			st = conn.prepareStatement(query.toString());
			st.setString(1, "");
			rs = st.executeQuery();

			while (rs.next()) {

				String content = "You subscribe to recieve alerts when a new issue is ready, the issue  "
						+ issueId + " is now publish ";
				emailSender e = new emailSender();
				e.sendEmail(rs.getString(1), "Subscription alert", content);
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
	 * Publishing a new issue
	 * 
	 * @return
	 */
	public String timeToPublishNewIssue() {

		method = "timeToPublishNewIssue";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";

		try {

			query = " SELECT * FROM issue a, article b WHERE a.created < ( CURRENT_DATE - INTERVAL 3 MONTH ) AND b.issueId = ? ";

			st = conn.prepareStatement(query.toString());
			st.setInt(1, 0);
			rs = st.executeQuery();
			if (rs.next()) {

				response = "Exist";
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
