package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Issue;

/**
 * Issue Model Class
 * 
 * 
 */
public class IssueModel {

	private String ERROR = this.getClass().toString();
	private String method = "";
	private boolean debug = false;

	/**
	 * Creating new Issue will return string as indication of being succesful or
	 * not
	 * 
	 * @return
	 */
	public String newIssue() {

		method = "newIssue";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {
			Issue issue = new Issue();
			response = issue.newIssue();
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * 
	 * @return
	 */
	public String timeToPublishNewIssue() {

		method = "timeToPublishNewIssue";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {
			Issue issue = new Issue();
			response = issue.timeToPublishNewIssue();
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

}
