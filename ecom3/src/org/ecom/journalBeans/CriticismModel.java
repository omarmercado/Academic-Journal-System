package org.ecom.journalBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.ecom.sql.Criticism;
import org.ecom.sql.Expertise;

/**
 * Model Class for critisim
 * 
 * 
 */
public class CriticismModel {

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

		String result = "";

		method = "setCriticism";

		if (debug) {
			System.out.println(method);
		}

		try {
			Criticism criticism = new Criticism();
			result = criticism.setCriticism(submissionId, content, response, userId, reviewId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
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

		String result = "";

		method = "respondCriticism";

		if (debug) {
			System.out.println(method);
		}

		try {
			Criticism criticism = new Criticism();
			result = criticism.respondCriticism(crtcismId, submissionId, response);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
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

		String result = "";

		method = "acceptCriticism";

		if (debug) {
			System.out.println(method);
		}

		try {
			Criticism criticism = new Criticism();
			result = criticism.acceptCriticism(crtcismId, submissionId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
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

		ArrayList result = new ArrayList();

		method = "getCriticismbySubId";

		if (debug) {
			System.out.println(method);
		}

		try {
			Criticism criticism = new Criticism();
			result = criticism.getCriticismbySubIdUserId(submissionId, userId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
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

		ArrayList result = new ArrayList();

		method = "getAllCriticismbySubId";

		if (debug) {
			System.out.println(method);
			System.out.println(submissionId);
		}

		try {
			Criticism criticism = new Criticism();
			result = criticism.getAllCriticismbySubId(submissionId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}
	
}
