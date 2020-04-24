package com.cegedim.fsm.exceptions;

public class FileNotFoundResponse {
	private String file;
	
	public FileNotFoundResponse(String file) {
		super();
		this.file = file;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
}
