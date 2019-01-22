package com.cs.ldapdemo.rest;

public class LdapUserNotFoundException extends RuntimeException {

	public LdapUserNotFoundException() {
	}

	public LdapUserNotFoundException(String message) {
		super(message);
	}

	public LdapUserNotFoundException(Throwable cause) {
		super(cause);
	}

	public LdapUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public LdapUserNotFoundException(String message, Throwable cause, boolean enableSuppression,
									 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
