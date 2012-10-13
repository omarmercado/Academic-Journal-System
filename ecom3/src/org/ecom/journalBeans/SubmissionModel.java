/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.bind.ParseConversionEvent;

import org.ecom.sql.Submission;

/**
 * Submission Model Class
 * 
 * 
 */
public class SubmissionModel {
	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Add a submission to database
	 * 
	 * @param Title
	 * @param Abstract
	 * @param users
	 * @param MainAuthorID
	 * @param keywords
	 * @return
	 */
	public String startNewSubmission(String Title, String Abstract,
			ArrayList users, String MainAuthorID, String keywords) {
		method = "startNewSubmission";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		String res = "";
		int result = 0;
		int submissionId = 0;
		try {
			Submission sub = new Submission();
			result = sub.setRevision(Title, Abstract); // Return Automatic
														// Generated Revision ID
			if (result > 0) {
				submissionId = sub.setSubmission(result, MainAuthorID);// Return
																		// Automatic
																		// Generated
																		// Submission
																		// ID
				res = String.valueOf(submissionId);
				if (submissionId > 0) {
					String fileCreation = sub.setFileRelation(submissionId,
							Title);
					if (fileCreation.equals("OK")) {
						response = sub.setMainAuthor(users, MainAuthorID,
								submissionId);
						if (response.equals("OK")) {
							response = sub.setKeywordsSub(keywords,
									submissionId);

							sub.sendEmailnewUser(Integer.valueOf(MainAuthorID));

						}
					}
				}
			}

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return res;
	}

	/**
	 * Return an arraylist of user's submissions
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getUserSubmission(int userId) {
		method = "getUserSubmission";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Submission sub = new Submission();
			response = sub.getUserSubmissions(userId); // Return List of
														// Submission by a
														// particula USer

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Return all available submissions as an arraylist
	 * 
	 * @return
	 */
	public ArrayList getAllSubmission() {
		method = "getAllSubmission";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Submission sub = new Submission();
			response = sub.getAllSubmissions(); // Return List of Submission by
												// a particula USer

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	public ArrayList getAllSubmissionForRev(int userId) {
		method = "getAllSubmissionForRev";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Submission sub = new Submission();
			response = sub.getAllSubmissionForRev(userId); // Return List of
															// Submission by a
															// particula USer

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Resubmit a submission
	 * 
	 * @param submissionId
	 * @param title
	 * @param Abstract
	 * @return
	 */
	public String reSubmission(int submissionId, String title, String Abstract) {
		method = "reSubmission";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		int revisionId = 0;
		try {
			Submission sub = new Submission();
			response = sub.setUpdateRevision(submissionId, title, Abstract);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Retrun arraylist of submissions who pass all the reviews to publish
	 * 
	 * @return
	 */
	public ArrayList getSubmissionToPublish() {
		method = "getSubmissionToPublish";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Submission sub = new Submission();
			response = sub.getSubmissionToPublish(); // Return List of
														// Submission waiting to
														// be publish

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * return file's related to submissions
	 * 
	 * @param submissionId
	 * @return
	 */
	public String getFileRelation(int submissionId) {
		method = "getFileRelation";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {
			Submission sub = new Submission();
			response = sub.getFileRelation(submissionId); // Return List of
															// Submission
															// waiting to be
															// publish

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * add a file to upload repository
	 * 
	 * @param submissionId
	 * @return
	 */
	public String setFileUpload(int submissionId) {
		method = "setFileUpload";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {
			Submission sub = new Submission();
			sub.setFileUpload(submissionId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Cehck if submission needs to send an email
	 * 
	 * @param email
	 * @return
	 */
	public String checkForSubmissionEmail(String email) {
		method = "checkForSubmissionEmail";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		int revisionId = 0;
		try {
			Submission sub = new Submission();
			response = sub.checkForSubmissionEmail(email);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}
}
