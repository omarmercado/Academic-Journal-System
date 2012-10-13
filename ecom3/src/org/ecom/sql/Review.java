package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.ecom.journalBeans.ConnPool;
import org.ecom.journalBeans.emailSender;

/**
 * 
 * Review DAO class
 * 
 */
public class Review {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Add a new review to a submission
	 * 
	 * @param submissionId
	 * @param userId
	 * @param roleId
	 * @param reviewerExpertiseId
	 * @param scoreId
	 * @param reviewText
	 * @param description
	 * @param content
	 * @param response
	 * @return
	 */
	public String newReview(int submissionId, int userId, int roleId,
			int reviewerExpertiseId, int scoreId, String reviewText,
			String description, String content, String response) {

		method = "newReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int criticisimId = 0;
		String result = "Inserting new review Failed";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			if (content.length() > 0) {

				criticisimId = setCrticism(description, content, "", 1);

			}

			query = "INSERT INTO review (submissionId, reviewerId, reviewerExpertiseId, scoreId, "
					+ " reviewText, criticisimId ) VALUES(?,?,?,?,?,?)";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			st.setInt(2, userId);
			st.setInt(3, reviewerExpertiseId);
			st.setInt(4, scoreId);
			st.setString(5, reviewText);
			st.setInt(6, criticisimId);
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
	 * Add a critisim to a submission
	 * 
	 * @param description
	 * @param content
	 * @param response
	 * @param cid
	 * @return
	 */
	public int setCrticism(String description, String content, String response,
			int cid) {

		method = "setCrticism";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO crticism (description, content, response ) VALUES(?,?,?)";
			st = conn.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, description);
			st.setString(2, content);
			st.setString(3, response);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
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

	public int newResponse(int critismId, String response) {

		method = "newResponse";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		int result = 0;
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE crticism SET response = ?  WHERE critismId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setString(1, response);
			st.setInt(2, critismId);

			result = st.executeUpdate();

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
	 * retrun a review of an particular user
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getReviewsfromUser(int userId) {

		method = "getReviewsfromUser";

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

			query = "SELECT b.submissionId,c.title, d.firstName, c.abstract, e.statusTitle, f.filepath,a.reviewId,c.version, b.statusId,b.mainAuthorId  FROM "
					+ " review a, submission b, revision c , user d, submission_status e, file f  WHERE a.reviewerId = ? AND a.submissionId = b.submissionId AND "
					+ " a.reviewerId = userId AND b.statusId = e.statusId AND b.submissionId = f.submissionId AND b.revisionId=c.revisionId AND b.statusId IN (2,4)";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// submissionId
				data.add(rs.getString(2));// title
				data.add(rs.getString(3));// firstName
				data.add(rs.getString(4));// abstract
				data.add(rs.getString(5));// statusTitle
				data.add(rs.getString(6));// filepath
				data.add(rs.getString(7));// review ID
				data.add(rs.getString(8));// version
				data.add(rs.getString(9));// statusId
				data.add(rs.getString(10));// Main Author Id
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
	 * retrun a comment from a temporary reviewer
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getReviewsfromUserTemp(int userId) {

		method = "getReviewsfromUserTemp";

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

			query = " SELECT b.submissionId,c.title, d.firstName, c.abstract, e.statusTitle, f.filepath,a.id,c.version, b.statusId,b.mainAuthorId  FROM "
					+ "  temporary_selected a, submission b, revision c , user d, submission_status e, file f  WHERE a.reviewerId = ? AND a.submissionId = b.submissionId AND "
					+ " a.reviewerId = userId AND b.statusId = e.statusId AND b.submissionId = f.submissionId AND b.revisionId=c.revisionId ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, userId);
			rs = st.executeQuery();
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// submission ID
				data.add(rs.getString(2));// title
				data.add(rs.getString(3));// firstName
				data.add(rs.getString(4));// abstract
				data.add(rs.getString(5));// statusTitle
				data.add(rs.getString(6));// filepath
				data.add(rs.getString(7));// temp ID
				data.add(rs.getString(8));// version
				data.add(rs.getString(9));// statusId
				data.add(rs.getString(10));// Main Author Id
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
	 * Select a submission to start reviewing
	 * 
	 * @param userId
	 * @param submissionId
	 * @return
	 */
	public String selectReview(int userId, int submissionId) {

		method = "selectReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ? WHERE   submissionId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 4);
			st.setInt(2, submissionId);
			int x = st.executeUpdate();

			if (x > 0) {

				st = null;
				query = "INSERT INTO review (submissionId, reviewerId, reviewerExpertiseId,scoreId,reviewText,criticisimId) VALUES(?,?,?,?,?,?)";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, submissionId);
				st.setInt(2, userId);
				st.setInt(3, 0);
				st.setInt(4, 0);
				st.setString(5, "");
				st.setInt(6, 0);
				int x2 = st.executeUpdate();

				if (x2 > 0) {
					response = "OK2";

					String Query = "SELECT count(*) FROM review WHERE submissionId = ?";

					st = conn.prepareStatement(Query);
					st.setInt(1, submissionId);
					rs = st.executeQuery();
					if (rs.next()) {

						response = "OK3";

						int total_reviews = rs.getInt(1);
						if (total_reviews > 2) {

							query = "UPDATE submission SET statusId = ? WHERE   submissionId = ? ";
							st = conn.prepareStatement(query.toString());
							st.setInt(1, 2);
							st.setInt(2, submissionId);
							int x3 = st.executeUpdate();

							if (x3 > 0) {
								response = "OK4";
							}
						}

					}

				} else {
					response = "INSERT REVIEW FAILED";
				}

			} else {
				response = "Update SUBMISSION FAILED";
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
	 * Select an issue for start reviewing by a temporary reviewer
	 * 
	 * @param userId
	 * @param submissionId
	 * @return
	 */
	public String selectReviewTemp(int userId, int submissionId) {

		method = "selectReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ? WHERE   submissionId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 5);// temporary review
			st.setInt(2, submissionId);
			int x = st.executeUpdate();

			query = "INSERT INTO temporary_selected (submissionId ,reviewerId) VALUES	(?,?)";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			st.setInt(2, userId);
			int x2 = st.executeUpdate();

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
	 * Delete temporary selections
	 * 
	 * @param userId
	 * @param submissionId
	 * @param update_status
	 * @return
	 */
	public String deleteTempSelect(int userId, int submissionId,
			String update_status) {

		method = "deleteTempSelect";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			if (update_status.equals("OK")) {
				query = "UPDATE submission SET statusId = ? WHERE   submissionId = ? ";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, 1);// submitted
				st.setInt(2, submissionId);
				int x = st.executeUpdate();
			}

			query = "DELETE FROM temporary_selected WHERE submissionId=? AND reviewerId=? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			st.setInt(2, userId);
			int x2 = st.executeUpdate();

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
	 * Add review
	 * 
	 * @param scoreId
	 * @param reviewerExpertiseId
	 * @param reviewId
	 * @param reviewText
	 * @return
	 */
	public String setReview(int scoreId, int reviewerExpertiseId, int reviewId,
			String reviewText) {

		method = "setReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE review SET scoreId = ?, reviewerExpertiseId = ? ,reviewText = ?, reviewDate = CURRENT_DATE   WHERE   reviewId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, scoreId);
			st.setInt(2, reviewerExpertiseId);
			st.setString(3, reviewText);
			st.setInt(4, reviewId);
			int x = st.executeUpdate();

			query = "SELECT c.email  FROM review a, submission b, user c  WHERE a.reviewId = ? AND a.submissionId = b.submissionId AND b.mainAuthorId = c.userId";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, reviewId);
			rs2 = st.executeQuery();

			String email = "";
			if (rs2.next()) {

				email = rs2.getString(1);

				String content = "A new review has been made to your submission, Log in to respond to any Criticism "
						+ "trough the web form, this is the content of the review, after one week no more changes are allowed  "
						+ " Review Content " + reviewText;
				emailSender e = new emailSender();
				e.sendEmail(email, "Review Content", content);

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
	 * Return all reviews related to a submission ID
	 * 
	 * @param submissionId
	 * @return
	 */
	public ArrayList getReviewsfromSub(int submissionId) {

		method = "getReviewsfromSub";

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

			query = "SELECT reviewText, firstName, reviewId, reviewerId,scoreName FROM review a, user, score b  WHERE submissionId = ? AND userId = reviewerId AND a.scoreId = b.scoreId ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// REVIEW TEXT
				data.add(rs.getString(2));// FIRST NAME
				data.add(rs.getString(3));// Review Id
				data.add(rs.getString(4));// Reviewer Id
				data.add(rs.getString(5));// scoreName
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
	 * Reject a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String rejectReview(int reviewId) {

		method = "rejectReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ?  WHERE   submissionId = (SELECT submissionId FROM review WHERE reviewId = ?) ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 1);
			st.setInt(2, reviewId);
			st.executeUpdate();

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
	 * Inform editor about a conflict in a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String conflictReview(int reviewId) {

		method = "conflictReview";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ?  WHERE   submissionId = (SELECT submissionId FROM review WHERE reviewId = ? ) ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 1);// submitted
			st.setInt(2, reviewId);
			int x = st.executeUpdate();

			if (x > 0) {
				x = 0;

				query = "DELETE FROM  comment  WHERE   submissionId = (SELECT submissionId FROM review WHERE reviewId = ? ) ";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, reviewId);
				x = st.executeUpdate();

				query = "DELETE FROM  review  WHERE   reviewId = ? ";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, reviewId);
				st.executeUpdate();

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
	 * return number of credits of a reviewer this number will be use if
	 * reviewer is an auther at the same time so they can use this credit for
	 * publishing their articles
	 * 
	 * @param submissionId
	 * @return
	 */
	public String ReviewersCredit(int submissionId) {

		method = "ReviewersCredit";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT  reviewerId  FROM  review WHERE submissionId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			rs2 = st.executeQuery();

			while (rs2.next()) {
				int reviewerId = rs2.getInt(1);// user ID
				query = "";
				query = "SELECT credit FROM credit WHERE userId = ?";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, reviewerId);
				rs = st.executeQuery();
				if (rs.next()) {
					int value = rs.getInt(1) + 1;
					query = "UPDATE credit SET credit = ?  WHERE userId = ?";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, value);
					st.setInt(2, reviewerId);
					int y2 = st.executeUpdate();

				} else {
					query = "INSERT INTO credit (userId,credit)values(?,?)";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, reviewerId);
					st.setInt(2, 1);
					int y3 = st.executeUpdate();
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
	 * Return review
	 * 
	 * @return
	 */
	public String ChooseMoreReviews() {

		method = "ChooseMoreReviews";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "select DISTINCT email from review a, submission b, user c, user_role d where (a.reviewerId = c.userId AND "
					+ " a.submissionId = b.submissionId AND c.userId = d.userId AND d.roleId = ?) "
					+ " AND reviewerId NOT IN (select reviewerId from review a, submission b where "
					+ " a.submissionId = b.submissionId AND statusId  IN (2,4))";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 2);
			rs2 = st.executeQuery();

			String email = "";
			while (rs2.next()) {

				email = rs2.getString(1);

				String content = "The Editorial department has noticed that You dont have any reviews assign, please select more papers to review  thank you  ";
				emailSender e = new emailSender();

				e.sendEmail(email, "Pick More reviews", content);

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
	 * Retrun a value and notifying a reviewer that they need to start reviewing
	 * their old select submissions(articles)
	 * 
	 * @return
	 */
	public String SubmitYourOldReviews() {

		method = "SubmitYourOldReviews";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT email FROM user a, review b WHERE userId = reviewerId AND reviewDate < ( CURRENT_DATE - INTERVAL 15 DAY ) AND scoreId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 0);
			rs2 = st.executeQuery();

			String email = "";
			while (rs2.next()) {

				email = rs2.getString(1);

				String content = "The Editorial department has noticed that You have pending reviews for more than 15 days, please submit the reviews for this papers  ";
				emailSender e = new emailSender();

				e.sendEmail(email, "Submission Received", content);

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
	 * Close a review
	 * 
	 * @return
	 */
	public String CloseReviews() {

		method = "CloseReviews";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT a.reviewId,a.reviewText,a.submissionId FROM review a,submission b  WHERE  a.scoreId <> ? AND  "
					+ "a.reviewDate < ( CURRENT_DATE - INTERVAL 5 DAY ) AND a.submissionId = b.submissionId ANd b.statusId IN (?,?)";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 0);
			st.setInt(2, 2);
			st.setInt(3, 4);
			rs = st.executeQuery();
			while (rs.next()) {
				int reviewId = rs.getInt(1);

				query = " SELECT COUNT(*) FROM review a,submission b WHERE a.submissionId = (SELECT submissionId FROM review WHERE reviewId = ? ) AND "
						+ "    a.scoreId <> ?  AND a.submissionId = b.submissionId ANd b.statusId IN (?,?) AND a.reviewDate < ( CURRENT_DATE - INTERVAL 5 DAY )";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, reviewId);
				st.setInt(2, 0);
				st.setInt(3, 2);
				st.setInt(4, 4);
				rs2 = st.executeQuery();

				if (rs2.next()) {

					if (rs2.getInt(1) > 2) {

						query = "UPDATE submission SET statusId = ?  WHERE   submissionId = (SELECT submissionId FROM review WHERE reviewId = ? ) ";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, 3);
						st.setInt(2, reviewId);
						st.executeUpdate();

						query = "SELECT email FROM submission a,user b  WHERE a.mainAuthorId = b.userId  AND a.submissionId = ? ";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, rs.getInt(3));
						rs3 = st.executeQuery();

						if (rs3.next()) {

							String content = rs.getString(2);
							emailSender e = new emailSender();
							e.sendEmail(rs3.getString(1), "Submission Review",
									content);
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
	 * Return text of a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String getReviewText(int reviewId) {

		method = "getReviewText";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;

		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT reviewText FROM review WHERE  reviewId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, reviewId);
			rs = st.executeQuery();
			while (rs.next()) {

				response = rs.getString(1);

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
	 * Reject an artivle to being reviewed
	 * 
	 * @param submissionId
	 * @return
	 */
	public String rejectArticle(int submissionId) {

		method = "rejectArticle";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		ResultSet rs = null;

		PreparedStatement st = null;
		String query = "";
		String response = "";

		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET statusId = ?  WHERE   submissionId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 20);
			st.setInt(2, submissionId);
			st.executeUpdate();

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
