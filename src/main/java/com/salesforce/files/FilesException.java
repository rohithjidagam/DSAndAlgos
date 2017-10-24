package com.salesforce.files;

/**
 * File Node Exception class.
 * @author Rohith Jidagam
 * @version 23 Oct 2017
 *
 */
public class FilesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilesException(String msg) {
		super(msg);
	}

	public FilesException(Throwable ex) {
		super(ex);
	}

	public FilesException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
