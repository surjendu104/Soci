package com.surjendu.blog.exceptions;

public class ApiException extends Exception {
	public ApiException(String message) {
		super(message);
	}
	public ApiException() {
		super();
	}
}
