package com.project.exceptions;

import java.io.Serializable;

public class ProjectException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProjectException() {
		super();
	}

	public ProjectException(String arg0) {
		super(arg0);
	}

	public ProjectException(String arg0, Exception e) {
		super(arg0, e);
	}

}
