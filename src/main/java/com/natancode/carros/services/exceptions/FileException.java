package com.natancode.carros.services.exceptions;

public class FileException extends RuntimeException { //Tratamento de exceptions
	
 	private static final long serialVersionUID = 1L;
 	
 	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}
}