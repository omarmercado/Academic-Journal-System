package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Article;
import org.ecom.sql.Issue;
import org.ecom.sql.Journal;

/**
 * Journal Model Class
 * 
 * 
 */
public class JournalModel {

	private String ERROR = this.getClass().toString();
	private String method = "";
	private boolean debug = false;

	/**
	 * Creating A Journal
	 * 
	 * @param Title
	 * @param objectives
	 * @return
	 */
	public String createJournal(String Title, String objectives) {

		method = "createJournal";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.newJournal(Title, objectives);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Return information of a journal as an arraylist
	 * 
	 * @return
	 */
	public ArrayList getJournslInfo() {

		method = "getJournslInfo";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Journal jou = new Journal();
			response = jou.getJournslInfo();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Insert a letter to database , this letter is in corresponding of an
	 * article
	 * 
	 * @param ArticleId
	 * @param letter
	 * @return
	 */
	public String postLetter(String ArticleId, String letter) {

		method = "postLetter";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.postLetter(ArticleId, letter);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Retrun a letter in related to an article
	 * 
	 * @param ArticleId
	 * @return
	 */
	public String getArticleLetter(String ArticleId) {

		method = "getArticleLetter";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.getArticleLetter(ArticleId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Add a user to subscribe the journal
	 * 
	 * @param issue
	 * @param keyword
	 * @param email
	 * @return
	 */
	public String subscribe(String issue, String keyword, String email) {

		method = "subscribe";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.subscribe(issue, keyword, email);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Retrun Template
	 * 
	 * @return
	 */
	public String getTemplate() {

		method = "getTemplate";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.getTemplate();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Return Array of Templates
	 * 
	 * @return
	 */
	public ArrayList getTemplates() {

		method = "getTemplates";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Journal jou = new Journal();
			response = jou.getTemplates();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * change a template for a journal
	 * 
	 * @param id
	 * @return
	 */
	public String changeTemp(int id) {
		method = "changeTemp";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.changeTemp(id);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Add a file to a submission
	 * 
	 * @param submissionId
	 * @return
	 */
	public String getFileSubmission(int submissionId) {

		method = "getFileSubmission";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.getFileSubmission(submissionId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Add a file to an article
	 * 
	 * @param articleId
	 * @return
	 */
	public String getFileArticle(int articleId) {

		method = "getFileArticle";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.getFileArticle(articleId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	public String resign(int userId) {

		method = "resign";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {

			Journal jou = new Journal();
			response = jou.resign(userId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}
}
