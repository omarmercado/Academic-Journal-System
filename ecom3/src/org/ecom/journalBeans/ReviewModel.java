/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Review;

/**
 * Review class Model
 * 
 * 
 */
public class ReviewModel {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Inser a new review to databsae
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

		String result = "";

		method = "newReview";

		if (debug) {
			System.out.println(method);
		}

		try {
			Review rev = new Review();
			result = rev.newReview(submissionId, userId, roleId,
					reviewerExpertiseId, scoreId, reviewText, description,
					content, response);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * change a value of a critism
	 * 
	 * @param description
	 * @param content
	 * @param response
	 * @param cid
	 * @return
	 */
	public int setCrticism(String description, String content, String response,
			int cid) {

		int result = 0;

		method = "setCrticism";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.setCrticism(description, content, response, cid);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Add new reponse to a critisim
	 * 
	 * @param critismId
	 * @param response
	 * @return
	 */
	public int newResponse(int critismId, String response) {
		int result = 0;

		method = "newResponse";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.newResponse(critismId, response);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Return a list of reviews belongs to a user
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getReviewsfromUser(int userId) {
		ArrayList result = new ArrayList();

		method = "getReviewsfromUser";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.getReviewsfromUser(userId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Return list of reviews from a user (temporary usage)
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList getReviewsfromUserTemp(int userId) {
		ArrayList result = new ArrayList();

		method = "getReviewsfromUser";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.getReviewsfromUserTemp(userId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Selecting a review based userId and submission Id
	 * 
	 * @param userId
	 * @param submissionId
	 * @return
	 */
	public String selectReview(int userId, int submissionId) {

		String result = "";

		method = "selectReview";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.selectReview(userId, submissionId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	public String selectReviewTemp(int userId, int submissionId) {

		String result = "";

		method = "selectReview";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.selectReviewTemp(userId, submissionId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Remove a template
	 * 
	 * @param userId
	 * @param submissionId
	 * @param update_status
	 * @return
	 */
	public String deleteTempSelect(int userId, int submissionId,
			String update_status) {

		String result = "";

		method = "deleteTempSelect";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.deleteTempSelect(userId, submissionId, update_status);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Add a review to an article
	 * 
	 * @param scoreId
	 * @param reviewerExpertiseId
	 * @param reviewId
	 * @param reviewText
	 * @return
	 */
	public String setReview(int scoreId, int reviewerExpertiseId, int reviewId,
			String reviewText) {

		String result = "";

		method = "newReview";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();

			result = rev.setReview(scoreId, reviewerExpertiseId, reviewId,
					reviewText);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * retrun a review from submission set
	 * 
	 * @param submissionId
	 * @return
	 */
	public ArrayList getReviewsfromSub(int submissionId) {
		ArrayList result = new ArrayList();

		method = "getReviewsfromSub";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.getReviewsfromSub(submissionId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Rejecting a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String rejectReview(int reviewId) {

		String result = "";

		method = "rejectReview";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.rejectReview(reviewId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * if there is a conflict between two reviews this function can take care of
	 * it
	 * 
	 * @param reviewId
	 * @return
	 */
	public String conflictReview(int reviewId) {

		String result = "";

		method = "conflictReview";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.conflictReview(reviewId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * Calculate the credit fo reviewers
	 * 
	 * @param submissionId
	 * @return
	 */
	public String ReviewersCredit(int submissionId) {

		String result = "";

		method = "ReviewersCredit";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.ReviewersCredit(submissionId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * return a string consist of reviews of an article
	 * 
	 * @return
	 */
	public String ChooseMoreReviews() {

		String result = "";

		method = "ChooseMoreReviews";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.ChooseMoreReviews();
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * add old review to database (experiemental function/depricated)
	 * 
	 * @return
	 */
	public String SubmitYourOldReviews() {

		String result = "";

		method = "SubmitYourOldReviews";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.SubmitYourOldReviews();
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	public String CloseReviews() {

		String result = "";

		method = "CloseReviews";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.CloseReviews();
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * return full text of a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String getReviewText(int reviewId) {

		String result = "";

		method = "getReviewText";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			result = rev.getReviewText(reviewId);
		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

	/**
	 * return full text of a review
	 * 
	 * @param reviewId
	 * @return
	 */
	public String rejectArticle(int submissionId) {

		String result = "";

		method = "rejectArticle";

		if (debug) {
			System.out.println(method);
		}
		try {
			Review rev = new Review();
			UserModel user = new UserModel();

			result = rev.rejectArticle(submissionId);

			rev.ReviewersCredit(submissionId);// give 1 credit to Revieres of
												// New Publish Article

			user.publishPendingArticles(submissionId, "", "");

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}
}
