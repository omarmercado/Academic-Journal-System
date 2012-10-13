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

import org.apache.commons.lang3.RandomStringUtils;
import org.ecom.journalBeans.ConnPool;
import org.ecom.journalBeans.emailSender;
import org.ecom.journalBeans.hashPass;

/**
 * User DAO class
 * 
 * 
 */
public class User {

	/** The ERROR. */
	private String ERROR = this.getClass().toString();
	private boolean debug = false;
	private String method = "";

	/**
	 * Return a user based on user and passowrd
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public ArrayList getUser(String username, String password) {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		ArrayList response = new ArrayList();
		ResultSet rs = null;
		String query = null;
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT a.userId, a.userName, b.roleId FROM user a,user_role b, role_list c  WHERE a.userName = ? "
					+ " and a.passWord = ? and b.userId = a.userId and  b.roleId = c.roleId ";
			st = conn.prepareStatement(query.toString());
			st.setString(1, username);
			hashPass ha = new hashPass();
			st.setString(2, ha.getMd5Digest(password));

			rs = st.executeQuery();

			if (rs.next()) {
				response.add(rs.getString("userId"));
				response.add(rs.getString("userName"));
				response.add(rs.getString("roleId"));
			}

		} catch (Exception ex) {
			System.out.println("getUser " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("getUser " + ERROR);
				} catch (Exception e) {
				}
			}
		}

		finally {
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
	 * add a new user to journal
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	public String newUser(String firstName, String lastName, String email,
			String userName, String password, String role) {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		String response = "";
		ResultSet rs = null;
		String query = null;

		response = "User Creation Failed";
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO user (firstName,lastName,email,userName,passWord) values(?,?,?,?,?) ";
			st = conn.prepareStatement(query.toString());
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, email);
			st.setString(4, userName);
			st.setString(5, password);
			int result = st.executeUpdate();

			if (result > 0) {

				query = "SELECT userId FROM user WHERE  userName = ? AND passWord = ?";
				st = conn.prepareStatement(query.toString());
				st.setString(1, userName);
				st.setString(2, password);
				rs = st.executeQuery();

				if (rs.next()) {

					String userID = rs.getString("userId");

					query = "INSERT INTO user_role (userId,roleId) values(?,?) ";
					st = conn.prepareStatement(query.toString());
					st.setString(1, userID);
					st.setString(2, role);

					int resultuser = st.executeUpdate();
					if (resultuser > 0) {
						response = "New User Succesful";
					}

				}
			}

		} catch (Exception ex) {
			try {
				System.out.println(ERROR + ex);
				st.close();
			} catch (Exception e) {
			}

			rs = null;
			if (conn != null) {
				try {
					conn.close();
					System.out.println("select " + ERROR);
				} catch (Exception e) {
				}
			}
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
	 * return all users which are available in journal
	 * 
	 * @return
	 */
	public ArrayList getAllUsers() {
		String method = "getAllUsers";
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

			query = "SELECT a.userId,a.firstName,a.lastName,a.email,c.roleName  ,a.userName, a.passWord  FROM user a, user_role b, role_list c WHERE "
					+ " a.userId=b.userId  AND b.roleId= c.roleId ";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// userId
				data.add(rs.getString(2));// firstName
				data.add(rs.getString(3));// lastName
				data.add(rs.getString(4));// email
				data.add(rs.getString(5));// roleName

				data.add(rs.getString(6));// userName
				data.add(rs.getString(7));// password
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
	 * upgrad an author to a reviewer
	 * 
	 * @param submissionId
	 * @return
	 */
	public String changeAuthorToReviewer(int submissionId) {
		method = "changeAuthorToReviewer";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		ResultSet rs = null;
		String response = "changeAuthorToReviewer Failed";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT mainAuthorId FROM submission WHERE submissionId = ?"; // take
																					// user
																					// ID
																					// from
																					// submission
			st = conn.prepareStatement(query.toString());
			st.setInt(1, submissionId);
			rs = st.executeQuery();

			if (rs.next()) {
				int userId = rs.getInt(1);

				rs = null;
				query = "SELECT roleId FROM user_role WHERE userId = ?";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, userId);
				rs = st.executeQuery();
				if (rs.next()) {

					int roleId = rs.getInt(1);
					rs = null;

					if (roleId == 1) {
						query = "UPDATE user_role SET roleId = ?  WHERE userId = ?";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, 4);
						st.setInt(2, userId);// User ID
						int y = st.executeUpdate();
						if (y > 0) {
							response = "OK";

							ArrayList userinfo = new ArrayList();
							userinfo = getUserInfo(userId);

							String content = "Your Submission have been Accepted, Log In to Select 3 Available reviews And star the Review Proccess";
							emailSender e = new emailSender();
							e.sendEmail(userinfo.get(2).toString(),
									"You Submission have been Accepted",
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
	 * publish an article which is pending
	 * 
	 * @param submissionId
	 * @param title
	 * @param Abstract
	 * @return
	 */
	public String publishPendingArticles(int submissionId, String title,
			String Abstract) {
		method = "publishPendingArticles";

		if (debug) {
			System.out.println(method);
			System.out.println(submissionId);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		int articleId = 0;
		String response = "publishPendingArticles Failed";
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
					int credit = rs.getInt(1);

					if (credit > 2) {

						// / submission Id tiene q ser el nuevo submissionId no
						// el q entra

						query = "SELECT a.submissionId,b.title,b.abstract  FROM submission a, revision b WHERE  a.revisionId = b.revisionId AND mainAuthorId = ? AND statusId = ?";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, reviewerId);
						st.setInt(2, 9);
						rs4 = st.executeQuery();

						if (rs4.next()) {

							int newSubmissionId = rs4.getInt(1);
							String newtitle = rs4.getString(2);
							String newabstract = rs4.getString(3);

							query = "UPDATE submission SET statusId = ? WHERE mainAuthorId = ? AND statusId = ?";
							st = conn.prepareStatement(query.toString());
							st.setInt(1, 10);
							st.setInt(2, reviewerId);
							st.setInt(3, 9);
							int x = st.executeUpdate();
							if (x > 0) {

								query = "";
								query = "INSERT INTO article (issueId, title, abstract, submissionId) VALUES(?,?,?,?)";
								st = conn.prepareStatement(query.toString(),
										Statement.RETURN_GENERATED_KEYS);
								st.setInt(1, 0);
								st.setString(2, newtitle);
								st.setString(3, newabstract);
								st.setInt(4, newSubmissionId);
								st.executeUpdate();

								rs3 = st.getGeneratedKeys();
								if (rs3.next()) {

									articleId = rs3.getInt(1);
									query = "";
									query = "UPDATE keyword SET articleId = ?  WHERE submissionId = ?";
									st = conn
											.prepareStatement(query.toString());
									st.setInt(1, articleId);
									st.setInt(2, newSubmissionId);// User ID
									st.executeUpdate();

								}

								credit = credit - 3;
								query = "";
								query = "UPDATE credit SET credit = ?  WHERE userId = ?";
								st = conn.prepareStatement(query.toString());
								st.setInt(1, credit);
								st.setInt(2, reviewerId);// User ID
								int y2 = st.executeUpdate();

								rs = null;
								query = "SELECT roleId FROM user_role WHERE userId = ?";
								st = conn.prepareStatement(query.toString());
								st.setInt(1, reviewerId);
								rs = st.executeQuery();
								if (rs.next()) {

									int roleId = rs.getInt(1);
									rs = null;

									if (roleId == 4) {
										query = "UPDATE user_role SET roleId = ?  WHERE userId = ?";
										st = conn.prepareStatement(query
												.toString());
										st.setInt(1, 1);
										st.setInt(2, reviewerId);// User ID
										st.executeUpdate();
									}
								}
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
	 * upate user information
	 * 
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param roleName
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public String updateUser(int userId, String firstName, String lastName,
			String email, String roleName, String userName, String passWord) {
		method = "updateUser";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		ResultSet rs = null;
		String response = "updateUser Failed";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "UPDATE user SET firstName=?, lastName=?,  email=?, userName=? WHERE userId = ?";
			st = conn.prepareStatement(query.toString());
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, email);
			st.setString(4, email);
			st.setInt(5, userId);
			int y = st.executeUpdate();

			query = "UPDATE user_role SET roleId=? WHERE userId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, Integer.valueOf(roleName));
			st.setInt(2, userId);

			int y2 = st.executeUpdate();

			String content = "User Informtaion   Username " + email
					+ "   First Name  " + firstName + "  Last Name  "
					+ lastName + " Role = " + roleName;
			emailSender e = new emailSender();
			e.sendEmail(email, "User Update", content);

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
	 * return all information of a particular user
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getUserInfo(int userId) {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		ArrayList response = new ArrayList();
		ResultSet rs = null;
		String query = null;
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT a.userId,a.firstName,a.lastName,a.email,c.roleName ,a.userName, a.passWord,b.roleId  FROM user a, user_role b, role_list c WHERE "
					+ "  a.userId=b.userId  AND b.roleId= c.roleId AND a.userId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, userId);

			rs = st.executeQuery();

			if (rs.next()) {
				response.add(rs.getString(1));// userId
				response.add(rs.getString(2));// firstName
				response.add(rs.getString(3));// lastName
				response.add(rs.getString(4));// email
				response.add(rs.getString(5));// roleName

				response.add(rs.getString(6));// userName
				response.add(rs.getString(7));// password

				response.add(rs.getString(8));// roleId

			}

		} catch (Exception ex) {
			System.out.println("getUserInfo " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("getUserInfo " + ERROR);
				} catch (Exception e) {
				}
			}
		} finally {
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
	 * return all roles which are availble inside the journal
	 * 
	 * @return
	 */
	public ArrayList getRoles() {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		ResultSet rs = null;
		String query = null;
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT  roleId, roleName FROM role_list";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// roleName
				data.add(rs.getString(2));// description
				response.add(data.clone());
				data.clear();

			}

		} catch (Exception ex) {
			System.out.println("getRoles " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("getRoles " + ERROR);
				} catch (Exception e) {
				}
			}
		} finally {
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
	 * add a user as mainauthor into the system
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return
	 */
	public int createMainAuthor(String firstName, String lastName, String email) {
		method = "createMainAuthor";

		if (debug) {
			System.out.println(method);
		}

		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		ResultSet rs = null;
		String response = "createMainAuthor Failed";
		int userId = 0;
		int userRole = 0;
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT a.userId, a.passWord, b.roleId FROM user a, user_role b  WHERE email = ? AND a.userId = b.userId";
			st = conn.prepareStatement(query.toString());
			st.setString(1, email.trim());
			rs = st.executeQuery();

			if (rs.next()) {

				userId = rs.getInt(1);
				String pass = rs.getString(2);
				userRole = rs.getInt(1);

				if (userRole != 2 && userRole != 3) {
					if (pass.trim().length() < 1) {
						String Newpass = RandomStringUtils
								.randomAlphanumeric(6);

						query = "UPDATE user SET firstName=?, lastName=?,  email=?, userName=?, passWord=? WHERE userId = ?";
						st = conn.prepareStatement(query.toString());
						st.setString(1, firstName);
						st.setString(2, lastName);
						st.setString(3, email);
						st.setString(4, email);
						st.setString(5, Newpass);
						st.setInt(6, userId);
						int y = st.executeUpdate();

						query = "UPDATE user_role SET roleId=? WHERE userId = ?";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, 1);
						st.setInt(2, userId);
						st.executeUpdate();
					}
				} else {
					userId = -999999;
				}
			} else {
				String Newpass = RandomStringUtils.randomAlphanumeric(6);
				newUser(firstName, lastName, email, email, Newpass, "1");

				query = "SELECT userId FROM user WHERE  userName = ? ";
				st = conn.prepareStatement(query.toString());
				st.setString(1, email);
				rs = st.executeQuery();
				if (rs.next()) {
					userId = rs.getInt(1);
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
		return userId;
	}

	/**
	 * Add a request from a (new/old) user to journal for being a reviewer
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param msg
	 * @return
	 */
	public int applyReviewer(String firstName, String lastName, String email,
			String msg) {

		method = "applyReviewer";
		if (debug) {
			System.out.println(method);
		}

		int response = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String query = "";
		try {
			// conn = conection_.getConection();
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "INSERT INTO reviewer_apply (firstName, lastName, email, msg) VALUES(?,?,?,?)";
			st = conn.prepareStatement(query.toString());
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, email);
			st.setString(4, msg);
			st.executeUpdate();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		} finally {
			try {
				st.close();
			} catch (Exception e) {
			}
			;
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
	 * retrun list of users who apply to be a reviewer
	 * 
	 * @return
	 */
	public ArrayList getReviewRoleApplications() {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		ArrayList response = new ArrayList();
		ArrayList data = new ArrayList();
		ResultSet rs = null;
		String query = null;
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT  id, firstName, lastName, email, msg  FROM reviewer_apply";
			st = conn.prepareStatement(query.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// id
				data.add(rs.getString(2));// firstName
				data.add(rs.getString(3));// lastName
				data.add(rs.getString(4));// email
				data.add(rs.getString(5));// msg
				response.add(data.clone());
				data.clear();

			}

		} catch (Exception ex) {
			System.out.println("getReviewRoleApplications " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("getReviewRoleApplications " + ERROR);
				} catch (Exception e) {
				}
			}
		} finally {
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
	 * Accpet a user as a reviewer
	 * 
	 * @param id
	 * @return
	 */
	public String acceptNewReviewer(int id) {

		Connection conn = null;
		// conn = conection_.getConection();

		PreparedStatement st = null;
		String response = "";
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String query = null;
		int userId = 0;
		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT  firstName, lastName, email, msg  FROM reviewer_apply WHERE id = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				query = "SELECT  userId  FROM user WHERE email = ?";
				st = conn.prepareStatement(query.toString());
				st.setString(1, rs.getString(3));
				rs3 = st.executeQuery();

				if (rs3.next()) {

					String content = "You have been Accepted as a reviewer, You Already Exist as A userusername ="
							+ rs.getString(3);
					emailSender e = new emailSender();
					e.sendEmail(rs.getString(3), "Review Application Accepted",
							content);

					query = "UPDATE user_role SET  roleId = ? WHERE userId = ? ";
					st = conn.prepareStatement(query.toString());
					st.setInt(1, 2);
					st.setInt(2, rs3.getInt(1));
					st.executeUpdate();

				} else {

					String Newpass = RandomStringUtils.randomAlphanumeric(6);

					query = "INSERT INTO  user (firstName, lastName, email,userName, passWord) values(?,?,?,?,?) ";
					st = conn.prepareStatement(query.toString(),
							Statement.RETURN_GENERATED_KEYS);
					st.setString(1, rs.getString(1));
					st.setString(2, rs.getString(2));
					st.setString(3, rs.getString(3));
					st.setString(4, rs.getString(3));
					st.setString(5, Newpass);
					int x = st.executeUpdate();
					rs2 = st.getGeneratedKeys();

					if (x > 0) {

						rs2.next();
						userId = rs2.getInt(1);

						query = "INSERT INTO  user_role (userId, roleId) values(?,?) ";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, userId);
						st.setInt(2, 2);
						st.executeUpdate();

						query = "DELETE FROM  reviewer_apply WHERE  id = ? ";
						st = conn.prepareStatement(query.toString());
						st.setInt(1, id);
						st.executeUpdate();

						String content = "You have been Accepted as a reviewer  username ="
								+ rs.getString(3) + "   password :  " + Newpass;
						emailSender e = new emailSender();
						e.sendEmail(rs.getString(3),
								"Review Application Accepted", content);

						query = "UPDATE user SET  passWord = ? WHERE userId =?  ";
						st = conn.prepareStatement(query.toString());
						hashPass p = new hashPass();
						String ha = p.getMd5Digest(Newpass);
						st.setString(1, ha);
						st.setInt(2, userId);
						st.executeUpdate();
					}
				}

			}

		} catch (Exception ex) {
			System.out.println("acceptNewReviewer " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("getReviewRoleApplications " + ERROR);
				} catch (Exception e) {
				}
			}
		} finally {
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
	 * Reject a user for being a reviwer inside the journal
	 * 
	 * @param id
	 * @return
	 */
	public String rejectNewReviewer(int id) {

		Connection conn = null;
		// conn = conection_.getConection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String response = "";

		String query = null;

		try {
			ConnPool ds = new ConnPool();
			conn = ds.getConnection();

			query = "SELECT email FROM  reviewer_apply WHERE  id = ? ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				query = "DELETE FROM  reviewer_apply WHERE  id = ? ";
				st = conn.prepareStatement(query.toString());
				st.setInt(1, id);
				st.executeUpdate();

				String content = "You have been Rejected as a reviewer ";
				emailSender e = new emailSender();
				e.sendEmail(rs.getString(1), "Review Application Rejected",
						content);
			}
		} catch (Exception ex) {
			System.out.println("rejectNewReviewer " + ERROR + ex);
			if (conn != null) {
				try {
					conn.close();
					System.out.println("rejectNewReviewer " + ERROR);
				} catch (Exception e) {
				}
			}
		} finally {
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
	 * Return all sub-author of an article
	 * 
	 * @param mainAuthorId
	 * @return
	 */
	public ArrayList getAllAuthorsSub(int mainAuthorId) {
		String method = "getAllAuthorsSub";
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

			query = "SELECT b.userId,c.firstName,c.lastName,c.email FROM main_author a,affiliation b,user c WHERE "
					+ " a.authorId = ? AND a.mainAuthorId = b.mainAuthorId AND b.userId = c.userId";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, mainAuthorId);
			rs = st.executeQuery();

			while (rs.next()) {
				data.add(rs.getString(1));// userId
				data.add(rs.getString(2));// firstName
				data.add(rs.getString(3));// lastName
				data.add(rs.getString(4));// email
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
	 * Return all information of main author of an article
	 * 
	 * @param mainAuthorId
	 * @return
	 */
	public ArrayList getMainAuthorInfo(int mainAuthorId) {
		String method = "getMainAuthorInfo";
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

			query = "SELECT userId,firstName, lastName, email FROM user b WHERE b.userId = ?";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, mainAuthorId);
			rs = st.executeQuery();

			if (rs.next()) {
				data.add(rs.getString(1));// userId
				data.add(rs.getString(2));// firstName
				data.add(rs.getString(3));// lastName
				data.add(rs.getString(4));// email
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
	 * Return information about a reviewer
	 * 
	 * @param reviewerId
	 * @return
	 */
	public ArrayList getReviewerProfile(int reviewerId) {
		String method = "getReviewerProfile";
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

			query = "SELECT a.submissionId,c.title,d.userName  FROM review a, submission b, revision c, user d WHERE "
					+ "a.submissionId = b.submissionId AND b.revisionId = c.revisionId AND a.reviewerId = ? AND b.mainAuthorId = userId ";
			st = conn.prepareStatement(query.toString());
			st.setInt(1, reviewerId);
			rs = st.executeQuery();

			if (rs.next()) {
				data.add(rs.getString(1));// submissionId
				data.add(rs.getString(2));// title
				data.add(rs.getString(3));// userName
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
}