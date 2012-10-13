package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Review;
import org.ecom.sql.Score;

/**
 * Score Model Class Represting Scores of each articles
 * 
 * 
 */
public class ScoreModel {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * Return an arraylist of scores
	 * 
	 * @return
	 */
	public ArrayList getScores() {

		ArrayList result = new ArrayList();

		method = "getScores";

		if (debug) {
			System.out.println(method);
		}

		try {
			Score score = new Score();
			result = score.getScores();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}

}
