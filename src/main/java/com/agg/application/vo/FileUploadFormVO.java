/**
 * htamada
 */
package com.agg.application.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author htamada
 *
 */
public class FileUploadFormVO {
	private List<MultipartFile> files;

	/**
	 * @param files
	 */
	public FileUploadFormVO() {
		super();
	}

	/**
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	
	
}
