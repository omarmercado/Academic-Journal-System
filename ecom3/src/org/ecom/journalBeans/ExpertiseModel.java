package org.ecom.journalBeans;

import java.util.ArrayList;

import org.ecom.sql.Expertise;
import org.ecom.sql.Score;

/**
 * Experise Model Class
 * 
 * 
 */
public class ExpertiseModel {

	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * List out all Expertise in an Arraylist
	 * 
	 * @return
	 */
	public ArrayList getExpertise() {

		ArrayList result = new ArrayList();

		method = "getExpertise";

		if (debug) {
			System.out.println(method);
		}

		try {
			Expertise expertise = new Expertise();
			result = expertise.getExpertise();

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return result;
	}
}
