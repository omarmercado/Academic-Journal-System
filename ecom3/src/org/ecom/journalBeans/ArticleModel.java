package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Article;
import org.ecom.sql.Submission;

/**
 * Article Model Class will take care of connectivity to database
 * 
 * 
 */
public class ArticleModel {

	private String ERROR = this.getClass().toString();
	private String method = "";
	private boolean debug = false;

	/**
	 * Get list of Users whom writting articles(Author)
	 * 
	 * @param UserId
	 * @return
	 */
	public ArrayList getArtcileUser(int UserId) {

		method = "getArtcileUser";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getArticleUser(UserId); // Return List of Articles by
													// User

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * List out All articles available in database
	 * 
	 * @return ArrayList
	 */
	public ArrayList getAllArtciles() {

		method = "getAllArtciles";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getAllArticles(); // Return List of Articles by User

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Add a document to publish queue
	 * 
	 * @param submissionId
	 * @param title
	 * @param Abstract
	 * @return
	 */
	public String publishDocument(int submissionId, String title,
			String Abstract) {

		method = "publishDocument";

		if (debug) {
			System.out.println(method);
		}

		String response = "";
		try {
			Article art = new Article();
			UserModel user = new UserModel();
			ReviewModel rev = new ReviewModel();

			response = art.publish(submissionId, title, Abstract); // Return
																	// List of
																	// result of
																	// Publish

			response = user.changeAuthorToReviewer(submissionId);// change
																	// author to
																	// TEMP so
																	// he will
																	// be able
																	// to do
																	// reviews
																	// AND take
																	// 3 point
																	// of AUTHOR
																	// credit

			response = rev.ReviewersCredit(submissionId);// give 1 credit to
															// Revieres of New
															// Publish Article

			response = user.publishPendingArticles(submissionId, title,
					Abstract);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Based on volume and issue number will return Articles indexes
	 * 
	 * @param volume
	 * @param issue
	 * @return
	 */
	public ArrayList getArticlesIndex(String volume, int issue) {

		method = "getArticlesIndex";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getArticlesIndex(volume, issue); // Return List of
															// Articles by User

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * List all Volumes
	 * 
	 * @return
	 */
	public ArrayList getVolumes() {

		method = "getVolumes";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getVolumes(); // Return List of Articles by User

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Return list of issues of a volume
	 * 
	 * @param volume
	 * @return
	 */
	public ArrayList getIssue(String volume) {

		method = "getIssue";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getIssue(volume); // Return List of Articles by User

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}

	/**
	 * Return information of an article
	 * 
	 * @param ArticleId
	 * @return
	 */
	public ArrayList getArticleInfo(String ArticleId) {

		method = "getArticleInfo";

		if (debug) {
			System.out.println(method);
		}

		ArrayList response = new ArrayList();
		int result = 0;
		try {
			Article art = new Article();
			response = art.getArticleInfo(ArticleId);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}
}
