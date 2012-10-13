/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.commons.lang3.RandomStringUtils;
import org.ecom.sql.Search;

/**
 * Search Model Class Representing Searching model for journal
 * 
 * 
 */
public class SearchModel {
	private boolean debug = false;
	private String ERROR = this.getClass().toString();
	private String method = "";

	/**
	 * based option search can be done options are : "title" , "keywords" ,
	 * "authors" , "date"
	 * 
	 * @param option
	 * @param search
	 * @return
	 */
	public ArrayList execSearch(String option, ArrayList search) {

		method = "execSearch";

		ArrayList response = new ArrayList();
		if (debug) {
			System.out.println(method);
			System.out.println(option);
			System.out.println(search);
		}

		try{
         Search search1 = new Search();
         response = search1.execSearch(option, search);

		} catch (Exception ex) {
			System.err.println(ERROR + " - " + method + " - " + ex);
		}
		return response;
	}
}
