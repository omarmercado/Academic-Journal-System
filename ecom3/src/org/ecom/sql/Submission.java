package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.ecom.journalBeans.ConnPool;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.emailSender;
import org.ecom.journalBeans.hashPass;

/**
 * 
 * Submission DAO class
 * 
 */
public class Submission {
	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Add a revision for a submission
	 * 
	 * @param Title
	 * @param Abstract
	 * @return
	 */
	public int setRevision(String Title, String Abstract) {

		method = "setRevision";

		if (debug) {
			System.out.println(method);
		}

		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO revision (title,abstract) VALUES(?,?)";
			st = conn.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, Title);
			st.setString(2, Abstract);

			st.executeUpdate();
			rs = st.getGeneratedKeys();

			if (rs.next()) {
				response = rs.getInt(1);
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
	 * Update revision
	 * 
	 * @param submissionId
	 * @param Title
	 * @param Abstract
	 * @return
	 */
	public String setUpdateRevision(int submissionId, String Title,
			String Abstract) {

		method = "setUpdateRevision";

		if (debug) {
			System.out.println(method);
		}

		String response = "Update revision Failed";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT b.version,a.revisionId FROM submission a, revision b WHERE a.revisionId = b.revisionId and a.submissionId =  ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			rs = st.executeQuery();

			if (rs.next()) {

				int version = rs.getInt(1) + 1;
				query = "UPDATE revision SET title = ?,abstract = ?,version =? WHERE revisionId =  ? ";
				st = conn.prepareStatement(query.toString());
				st.setString(1, Title);
				st.setString(2, Abstract);
				st.setInt(3, version);
				st.setInt(4, rs.getInt(2));
				int z = st.executeUpdate();

				if (z > 0) {
					response = "OK";
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
	 * accept a submission
	 * 
	 * @param id
	 * @param MainAuthorID
	 * @return
	 */
	public int setSubmission(int id, String MainAuthorID) {

		method = "setSubmission";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;

		try {

			query = "INSERT INTO submission (statusId,revisionId,mainAuthorId) values(?,?,?) ";
			st = conn.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, 8);// waiting for file
			st.setInt(2, id);
			st.setString(3, MainAuthorID);
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
	 * Attach a file to a submission
	 * 
	 * @param submissionId
	 * @return
	 */
	public int setFileUpload(int submissionId) {

		method = "setFileUpload";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;

		try {

			query = "SELECT  *  FROM  submission  WHERE  submissionId = ? AND  statusId  <> ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			st.setInt(2, 8);
			rs = st.executeQuery();

			if (rs.next()) {

			} else {

				query = "UPDATE submission SET statusId = ? WHERE submissionId = ? ";
				st = conn.prepareStatement(query.toString(),
						Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, 1);//
				st.setInt(2, submissionId);
				st.executeUpdate();
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getInt(1);
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
		return result;
	}

	/**
	 * Add a file to a submission
	 * 
	 * @param SubmitedId
	 * @param title
	 * @return
	 */
	public String setFileRelation(int SubmitedId, String title) {

		method = "setFileRelation";

		if (debug) {
			System.out.println(method);
		}

		String response = "File Creation Failed";
		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		String fileQuery = "";

		try {
			String url = "";

			fileQuery = "INSERT INTO file (filename,filepath, submissionId,version) values(?,?,?,?) ";
			st = conn.prepareStatement(fileQuery,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, title);// filename PDF Tilte
			st.setString(2, url);// Path for PDF file
			st.setInt(3, SubmitedId);// Path for PDF file
			st.setInt(4, 0);// Path for PDF file
			int result = st.executeUpdate();

			if (result > 0) {
				response = "OK";
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
	 * Add an author to main author for a submission
	 * 
	 * @param users
	 * @param MainAuthorID
	 * @param SubmissionId
	 * @return
	 */
	public String setMainAuthor(ArrayList users, String MainAuthorID,
			int SubmissionId) {
		method = "setMainAuthor";

		if (debug) {
			System.out.println(method);
			System.out.println(MainAuthorID);
			System.out.println(users);
			System.out.println(SubmissionId);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String query = "";
		String response = "Main Author Failed";
		int main_author_id = 0;
		int result = 0;
		try {
			ArrayList user = new ArrayList();
			query = "INSERT INTO main_author (submissionId,authorId) values(?,?) ";
			st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, SubmissionId);// Submission ID
			st.setInt(2, Integer.valueOf(MainAuthorID));
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				main_author_id = rs.getInt(1);
				response = "OK";
			}

			for (int x = 0; x < users.size(); x++) {
				user = (ArrayList) users.get(x);

				query = "SELECT userId,passWord FROM user WHERE email = ?";
				st = conn.prepareStatement(query.toString());
				st.setString(1, user.get(2).toString().trim());
				rs3 = st.executeQuery();

				if (rs3.next()) {

					query = "INSERT INTO affiliation (userId,mainAuthorId) values(?,?) ";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, rs3.getInt(1));
					st.setInt(2, main_author_id);
					result = st.executeUpdate();

				} else {
					query = "INSERT INTO user (firstName,lastName,email,userName) values(?,?,?,?) ";
					st = conn.prepareStatement(query.toString(),
							Statement.RETURN_GENERATED_KEYS);
					st.setString(1, user.get(0).toString());// Main Author ID
					st.setString(2, user.get(1).toString());
					st.setString(3, user.get(2).toString());
					st.setString(4, user.get(2).toString());
					st.executeUpdate();
					rs2 = st.getGeneratedKeys();
					if (rs2.next()) {
						query = "INSERT INTO affiliation (userId,mainAuthorId) values(?,?) ";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, rs2.getInt(1));
						st.setInt(2, main_author_id);
						result = st.executeUpdate();
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
	 * set keywords for a submission
	 * 
	 * @param keywords
	 * @param submissionId
	 * @return
	 */
	public String setKeywordsSub(String keywords, int submissionId) {
		method = "setKeywordsSub";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "Adding Keyword Failed";
		int result = 0;
		try {
			String[] results = keywords.split(",");
			int x = 0;
			for (String string : results) {
				query = "INSERT INTO keyword (articleId,keyword,submissionId) values(?,?,?)";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, 0);
				st.setString(2, string);
				st.setInt(3, submissionId);
				result = st.executeUpdate();
				x++;
			}

			if (result > 0) {
				response = "OK";
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
	 * return all submissions of an user
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getUserSubmissions(int userId) {

		method = "getUserSubmissions";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {

			Query = "SELECT a.submissionId, c.title, c.abstract  ,f.filepath, b.statusTitle,a.mainAuthorId FROM submission a, submission_status b, revision c, main_author d, file f  WHERE "
					+ " a.statusId = b.statusId AND  a.revisionId = c.revisionId AND  a.mainAuthorId = ? AND a.submissionId = f.submissionId "
					+ " AND a.submissionId = f.submissionId AND d.submissionId = a.submissionId";

			st = conn.prepareStatement(Query);
			// st.setString(1, "submited");
			st.setInt(1, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1).toString());// submission ID
				data.add(rs.getString(2).toString());// submission Title
				data.add(rs.getString(3).toString());// submission Abstract
				data.add(rs.getString(4).toString());// submission FilePath
				data.add(rs.getString(5).toString());// submission status
				data.add(rs.getString(6).toString());// main author Id
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
	 * Return all submission in journal
	 * 
	 * @return
	 */
	public ArrayList getAllSubmissions() {

		method = "getAllSubmissions";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {

			Query = "SELECT a.submissionId,c.title, c.abstract  FROM submission a, submission_status b, revision c   WHERE a.statusId = b.statusId  AND a.revisionId = c.revisionId AND a.statusId = ? ";

			st = conn.prepareStatement(Query);
			st.setInt(1, 1);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1).toString());// submission ID
				data.add(rs.getString(2).toString());// submission Title
				data.add(rs.getString(3).toString());// submission Abstract
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
	 * Return submission which are waiting to being reviewed
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getAllSubmissionForRev(int userId) {

		method = "getAllSubmissionForRev";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String Query = "";

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {

			Query = "SELECT a.submissionId  FROM submission a, submission_status b, revision c   WHERE "
					+ " a.statusId = b.statusId  AND a.revisionId = c.revisionId AND a.statusId  IN ( ? ,? )  AND mainAuthorId <> ? AND"
					+ "  submissionDate < ( CURRENT_DATE - INTERVAL 5 DAY )";

			st = conn.prepareStatement(Query);
			st.setInt(1, 1);
			st.setInt(2, 4);
			st.setInt(3, userId);
			rs2 = st.executeQuery();

			ReviewModel rev = new ReviewModel();
			int x = 0;
			while (rs2.next()) {
				x++;
				int submissionId = rs2.getInt(1);
				rev.selectReview(userId, submissionId);

				if (x == 2) {
					break;
				}
			}

			Query = "SELECT a.submissionId,c.title, c.abstract,  a.mainAuthorId  FROM submission a, submission_status b, revision c   WHERE "
					+ " a.statusId = b.statusId  AND a.revisionId = c.revisionId AND a.statusId  IN ( ? ,? )  AND mainAuthorId <> ? "
					+ " AND a.submissionId NOT IN (SELECT submissionId FROM review WHERE reviewerId = ?) AND  "
					+ " a.submissionId NOT IN (SELECT submissionId FROM temporary_selected WHERE reviewerId = ? ) ";

			st = conn.prepareStatement(Query);
			st.setInt(1, 1);
			st.setInt(2, 4);
			st.setInt(3, userId);
			st.setInt(4, userId);
			st.setInt(5, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1).toString());// submission ID
				data.add(rs.getString(2).toString());// submission Title
				data.add(rs.getString(3).toString());// submission Abstract
				data.add(rs.getString(4).toString());// submission Abstract
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

	public String reSubmission(int submissionId, int revisionId) {
		method = "reSubmission";

		if (debug) {
			System.out.println(method);
		}
		Connection conn = null;

		PreparedStatement st = null;
		String query = "";
		String response = "reSubmission Failed";

		try {

			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE submission SET  revisionId = ? WHERE submissionId =?  ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, revisionId);
			st.setInt(2, submissionId);

			int x = st.executeUpdate();

			if (x > 0) {
				response = "OK";
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
	 * retrun submission which are ready to be published
	 * 
	 * @return
	 */
	public ArrayList getSubmissionToPublish() {

		method = "getSubmissionToPublish";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		ConnPool ds = new ConnPool();
		conn = ds.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();

		try {

			Query = "SELECT a.submissionId,a.mainAuthorId,b.title,b.abstract FROM submission a, revision b WHERE b.revisionId = a.revisionId AND statusId = ?";
			st = conn.prepareStatement(Query);
			st.setInt(1, 3);// 3 = waitong to be publish by editor
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1).toString());// submission ID
				data.add(rs.getString(2).toString());// submission Main Author
				data.add(rs.getString(3).toString());// submission Title
				data.add(rs.getString(4).toString());// submission Abstract
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
	 * return file list attached to a submission
	 * 
	 * @param submissionId
	 * @return
	 */
	public String getFileRelation(int submissionId) {

		method = "getFileRelation";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		String response = "";
		ArrayList data = new ArrayList();

		try {

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			Query = "SELECT  fileId  FROM  file  WHERE  submissionId = ?";
			st = conn.prepareStatement(Query);
			st.setInt(1, submissionId);
			rs = st.executeQuery();

			if (rs.next()) {
				response = rs.getString(1).toString();// fileID
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
	 * send an email to new user after completing the submission
	 * 
	 * @param usderId
	 * @return
	 */
	public String sendEmailnewUser(int usderId) {

		method = "sendEmail";

		if (debug) {
			System.out.println(method);
			System.out.println(usderId);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		String response = "";
		ArrayList data = new ArrayList();

		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			Query = "SELECT  email,passWord  FROM  user  WHERE  userId = ?";
			st = conn.prepareStatement(Query);
			st.setInt(1, usderId);
			rs = st.executeQuery();

			if (rs.next()) {
				String email = rs.getString(1).toString();
				String pass = rs.getString(2).toString();

				if (pass.length() > 10) {

					String content = "Subbimission has been received if you havent upload a file for the submission doit as soon as possible  "
							+ " You are already a registered user .  thank you  ";
					emailSender e = new emailSender();
					System.out.println(email);
					e.sendEmail(email, "Submission Received", content);

				} else {

					String content = "Subbimission has been received if you havent upload a file for the submission doit as soon as possible  email :"
							+ " "
							+ email
							+ "  password "
							+ pass
							+ " thank you  ";
					emailSender e = new emailSender();
					System.out.println(email);
					e.sendEmail(email, "Submission Received", content);

					String query = "UPDATE user SET  passWord = ? WHERE userId =?  ";
					st = conn.prepareStatement(query.toString());
					hashPass p = new hashPass();
					String ha = p.getMd5Digest(pass);
					st.setString(1, ha);
					st.setInt(2, usderId);
					st.executeUpdate();
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
	 * send an email to user if their submission is going to be published as an
	 * article
	 * 
	 * @param email
	 * @return
	 */
	public String checkForSubmissionEmail(String email) {

		method = "checkForSubmissionEmail";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		// conn = conection_.getConection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String Query = "";

		String response = "OK";

		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			Query = "SELECT * FROM submission WHERE statusId <> ? AND mainAuthorId = (SELECT userId FROM user WHERE userName = ? LIMIT 1) ";

			st = conn.prepareStatement(Query);
			st.setInt(1, 10);
			st.setString(2, email);

			System.out.println(Query);
			System.out.println(email);

			rs = st.executeQuery();

			if (rs.next()) {
				response = "NO";
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