package org.ecom.journalController;

import org.springframework.stereotype.Component;

/**
 * Session Controller class
 * 
 * 
 */
@Component("sesion")
public class sesionInfo {

	public sesionInfo sesion;

	public String Username = "";
	public String userRole = "0";
	public int userId = 0;

	public String getUsername() {
		return this.Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
