package com.natancode.carros.services.exceptions;

public class AuthorizationException extends RuntimeException { //Tratamento de exceptions
 	private static final long serialVersionUID = 1L;
 	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}