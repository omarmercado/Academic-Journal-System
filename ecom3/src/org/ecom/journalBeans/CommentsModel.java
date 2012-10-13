package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Comment;

/**
 * Model class for comments and messages
 * 
 * 
 */
public class CommentsModel {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Insert new comment to database
	 * 
	 * @param coment
	 * @param submissionId
	 * @param response
	 * @param userId
	 * @param roleId
	 * @param title
	 * @return
	 */
	public String newComments(String coment, int submissionId, String response,
			int userId, int roleId, String title) {

		String res = "";
		try {
			Comment com = new Comment();
			res = com.setComent(coment, submissionId, response, userId, roleId,
					title);
		} catch (Exception ex) {
			System.err.println(method + "  " + ERROR + "  " + ex);
		}

		return res;
	}

	/**
	 * Retrun list of comments based on submission ID (each submission ID
	 * belongs to one upload)
	 * 
	 * @param submissionId
	 * @return
	 */
	public ArrayList getComments(int submissionId) {

		ArrayList res = new ArrayList();
		try {
			Comment com = new Comment();

			res = com.getComments(submissionId);

		} catch (Exception ex) {
			System.err.println(method + "  " + ERROR + "  " + ex);
		}

		return res;
	}

	/**
	 * Insert comment to table from a user
	 * 
	 * @param submissionId
	 * @param userId
	 * @return
	 */
	public ArrayList getCommentsByUser(int submissionId, int userId) {

		ArrayList res = new ArrayList();
		try {
			Comment com = new Comment();

			res = com.getCommentsByUser(submissionId, userId);

		} catch (Exception ex) {
			System.err.println(method + "  " + ERROR + "  " + ex);
		}

		return res;
	}

	/**
	 * Add response to a comment
	 * 
	 * @param commentId
	 * @param response
	 * @return
	 */
	public String responseCom(int commentId, String response) {

		String res = "";
		try {
			Comment com = new Comment();
			res = com.responseCom(commentId, response);
		} catch (Exception ex) {
			System.err.println(method + "  " + ERROR + "  " + ex);
		}

		return res;
	}

	/**
	 * Return list of editor who edit a comments
	 * 
	 * @return
	 */
	public ArrayList getListComEditor() {

		ArrayList res = new ArrayList();
		try {
			Comment com = new Comment();

			res = com.getListComEditor();

		} catch (Exception ex) {
			System.err.println(method + "  " + ERROR + "  " + ex);
		}

		return res;
	}

}
