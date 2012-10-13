package org.ecom.journalBeans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Using this class to hash the passwords for better security NOTE : this class
 * will use MD5 and there is no way to return the hashed password back
 * 
 */
public class hashPass {

	/**
	 * Hash a plain text into MD5
	 * 
	 * @param pInput
	 * @return
	 */
	public String getMd5Digest(String pInput) {

		BigInteger lHashInt = null;
		try {

			MessageDigest lDigest = MessageDigest.getInstance("MD5");
			lDigest.update(pInput.getBytes());
			lHashInt = new BigInteger(1, lDigest.digest());

		} catch (NoSuchAlgorithmException lException) {
			throw new RuntimeException(lException);
		}
		return String.format("%1$032X", lHashInt);
	}

}
