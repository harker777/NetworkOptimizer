/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.controller;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Iaroslav_Mazai
 */
public class UploadedFile {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
