/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalBeans;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Upload Model Class
 * 
 * 
 */
public class UploadModel {

	private String name = null;
	private CommonsMultipartFile file = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
		this.name = file.getOriginalFilename();
	}
}