/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.journalController.sesionInfo;
import org.ecom.sql.Article;
import org.ecom.sql.User;

/**
 * User Model Class Represting a user in journal system
 * 
 */
public class UserModel {

	private String Error = this.getClass().toString();

	/**
	 * Signing up a user to journal
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	public String SignUp(String firstName, String lastName, String email,
			String userName, String password, String role) {
		String response = "User registration Failed";
		try {

			User newUser = new User();

			response = newUser.newUser(firstName, lastName, email, userName,
					password, role);

		} catch (Exception e) {
			System.err.println(Error + "_SignUp " + e);
		}

		return response;
	}

	/**
	 * Check for sigining in a user
	 * 
	 * @param userName
	 * @param password
	 * @param sesion
	 * @return
	 */
	public String SignIn(String userName, String password, sesionInfo sesion) {

		ArrayList userinfo = new ArrayList();
		String response = "User Doesnt Exist";

		try {

			User getUser = new User();

			userinfo = getUser.getUser(userName, password);

			if (!userinfo.isEmpty()) {

				sesion.setUserId(Integer.valueOf(userinfo.get(0).toString()));
				sesion.setUsername(userinfo.get(1).toString());
				sesion.setUserRole(userinfo.get(2).toString());
				response = "Log In Succesful";
			}

		} catch (Exception e) {
			System.err.println(Error + "_SignIn " + e);
		}

		return response;

	}

	/**
	 * List of all users
	 * 
	 * @return
	 */
	public ArrayList getAllUsers() {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getAllUsers(); // Return List of Articles by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Check if there is a relation between author and reviewer exist
	 * 
	 * @param submissionId
	 * @return
	 */
	public String changeAuthorToReviewer(int submissionId) {

		String response = "";
		try {
			User use = new User();
			response = use.changeAuthorToReviewer(submissionId); // Change
																	// Author To
																	// Temporary
																	// Review

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Publish all articles need to be published
	 * 
	 * @param submissionId
	 * @param title
	 * @param Abstract
	 * @return
	 */
	public String publishPendingArticles(int submissionId, String title,
			String Abstract) {

		String response = "";
		try {
			User use = new User();
			response = use
					.publishPendingArticles(submissionId, title, Abstract); // Change
																			// Author
																			// To
																			// Temporary
																			// Review

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Update user information
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

		String response = "";
		try {
			User use = new User();
			response = use.updateUser(userId, firstName, lastName, email,
					roleName, userName, passWord); // Change Author To Temporary
													// Review

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Get user information as an arraylist
	 * 
	 * @param UserId
	 * @return
	 */
	public ArrayList getUserInfo(int UserId) {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getUserInfo(UserId); // Return List of Articles by
												// User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * List of available roles in journal
	 * 
	 * @return
	 */
	public ArrayList getRoles() {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getRoles(); // Return List of Articles by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Creating a user for main author
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return
	 */
	public int createMainAuthor(String firstName, String lastName, String email) {

		int result = 0;
		try {
			User use = new User();
			result = use.createMainAuthor(firstName, lastName, email); // Return
																		// List
																		// of
																		// Articles
																		// by
																		// User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return result;
	}

	/**
	 * Update database for and uprade user to reviewer
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param msg
	 * @return
	 */
	public int applyReviewer(String firstName, String lastName, String email,
			String msg) {

		int result = 0;
		try {
			User use = new User();
			result = use.applyReviewer(firstName, lastName, email, msg); // Return
																			// List
																			// of
																			// Articles
																			// by
																			// User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return result;
	}

	/**
	 * return an arraylist of reviewers
	 * 
	 * @return
	 */
	public ArrayList getReviewRoleApplications() {

		ArrayList result = new ArrayList();
		try {
			User use = new User();
			result = use.getReviewRoleApplications(); // Return List of Articles
														// by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return result;
	}

	/**
	 * Accept a user requested to be reviewer
	 * 
	 * @param id
	 * @return
	 */
	public String acceptNewReviewer(int id) {

		String result = "";
		try {
			User use = new User();
			result = use.acceptNewReviewer(id); // Return List of Articles by
												// User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return result;
	}

	/**
	 * decline a user to be a reviewer
	 * 
	 * @param id
	 * @return
	 */
	public String rejectNewReviewer(int id) {

		String result = "";
		try {
			User use = new User();
			result = use.rejectNewReviewer(id); // Return List of Articles by
												// User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return result;
	}

	/**
	 * Return all authors submissions
	 * 
	 * @param mainAuthor
	 * @return
	 */
	public ArrayList getAllAuthorsSub(int mainAuthor) {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getAllAuthorsSub(mainAuthor); // Return List of
															// Articles by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * Return information of main author of an article
	 * 
	 * @param mainAuthor
	 * @return
	 */
	public ArrayList getMainAuthorInfo(int mainAuthor) {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getMainAuthorInfo(mainAuthor); // Return List of
															// Articles by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

	/**
	 * return information regarding to a reviewer
	 * 
	 * @param reviewerId
	 * @return
	 */
	public ArrayList getReviewerProfile(int reviewerId) {

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			User use = new User();
			response = use.getReviewerProfile(reviewerId); // Return List of
															// Articles by User

		} catch (Exception ex) { // System.err.println(ERROR
									// +" - "+method+" - "+ ex);
		}
		return response;
	}

}
