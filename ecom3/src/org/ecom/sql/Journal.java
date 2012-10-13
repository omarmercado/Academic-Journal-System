package org.ecom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.ecom.journalBeans.ConnPool;

/**
 * Journal DAO class
 * 
 * 
 */
public class Journal {
	private String ERROR = this.getClass().toString();

	/**
	 * Create a new journal
	 * 
	 * @param Title
	 * @param Obj
	 * @return
	 */
	public String newJournal(String Title, String Obj) {
		String method = "newJournal";
		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();

			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT * FROM journal ";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();
			if (rs.next()) {

				query = "UPDATE journal SET title = ?, description = ? ";
				st = conn.prepareStatement(query.toString());
				st.setString(1, Title);
				st.setString(2, Obj);
				st.executeUpdate();

			} else {
				query = "INSERT INTO journal (title,description) VALUES(?,?)";
				st = conn.prepareStatement(query.toString());
				st.setString(1, Title);
				st.setString(2, Obj);
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
		return String.valueOf(response);
	}

	/**
	 * Retrun all information of a journal
	 * 
	 * @return
	 */
	public ArrayList getJournslInfo() {

		String method = "getJournslInfo";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		ArrayList response = new ArrayList();
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT title,description,templateId FROM journal ";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				response.add(rs.getString(1));
				response.add(rs.getString(2));
				response.add(rs.getString(3));

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
	 * Send a Commemt(Letter) to journal
	 * 
	 * @param ArticleId
	 * @param Letter
	 * @return
	 */
	public String postLetter(String ArticleId, String Letter) {
		String method = "postLetter";
		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO letter (ArticleId,letter) VALUES(?,?)";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, Integer.valueOf(ArticleId));
			st.setString(2, Letter);
			response = st.executeUpdate();

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
		return String.valueOf(response);
	}

	/**
	 * Return Comments(Letters) related to an article
	 * 
	 * @param ArticleId
	 * @return
	 */
	public String getArticleLetter(String ArticleId) {
		String method = "postLetter";
		String response = "";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT letter FROM letter WHERE ArticleId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, Integer.valueOf(ArticleId));
			rs = st.executeQuery();
			if (rs.next()) {
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
	 * Subscribe to an issue
	 * 
	 * @param issue
	 * @param keyword
	 * @param email
	 * @return
	 */
	public String subscribe(String issue, String keyword, String email) {
		String method = "subscribe";
		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO subscription (issue, keyword, email) VALUES(?,?,?)";
			st = conn.prepareStatement(query.toString());
			st.setString(1, issue);
			st.setString(2, keyword);
			st.setString(3, email);
			response = st.executeUpdate();

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
		return String.valueOf(response);
	}

	/**
	 * Return CSS template
	 * 
	 * @return
	 */
	public String getTemplate() {
		String method = "getTemplate";
		String response = "";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT template_filename FROM template WHERE selected = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 1);
			rs = st.executeQuery();
			if (rs.next()) {
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
	 * List all CSS templates
	 * 
	 * @return
	 */
	public ArrayList getTemplates() {
		String method = "getTemplates";
		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT template_id,template_name  FROM template ";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();
			while (rs.next()) {
				data.add(rs.getString(1));
				data.add(rs.getString(2));
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
	 * Change template
	 * 
	 * @param Id
	 * @return
	 */
	public String changeTemp(int Id) {
		String method = "changeTemp";
		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE  template SET  selected = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 0);
			st.executeUpdate();

			query = "UPDATE  template SET  selected = ? WHERE  template_id = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, 1);
			st.setInt(2, Id);
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
		return String.valueOf(response);
	}

	/**
	 * List file attached to a submission
	 * 
	 * @param submissionId
	 * @return
	 */
	public String getFileSubmission(int submissionId) {

		String method = "getFileSubmission";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT fileId FROM file WHERE submissionId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			rs = st.executeQuery();
			if (rs.next()) {
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
	 * Return a file attached to an article
	 * 
	 * @param articleId
	 * @return
	 */
	public String getFileArticle(int articleId) {

		String method = "getFileArticle";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT fileId FROM article a,submission b, file c  WHERE a.submissionId = b.submissionId AND a.submissionId = c.submissionId AND articleId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, articleId);
			rs = st.executeQuery();
			if (rs.next()) {
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
	 * Resign from being an editor
	 * 
	 * @param userId
	 * @return
	 */
	public String resign(int userId) {

		String method = "resign";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "";
		String response = "";
		int res = 0;
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT COUNT(*) FROM user a,user_role b WHERE a.userId <> ? AND a.userId = b.userId AND roleId = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, userId);
			st.setInt(2, 3);
			rs = st.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);

				if (res > 0) {

					query = "UPDATE  user_role SET  roleId = ? WHERE userId = ?";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, 10);
					st.setInt(2, userId);
					st.executeUpdate();
					response = "OK";
				} else {

					response = "You need to asign a new Editor before Resigning";
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

}
