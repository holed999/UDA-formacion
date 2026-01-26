package com.ejie.zz99.common.exceptions;

public class AppRuntimeException extends RuntimeException {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for AppRuntimeException.
	 */
	public AppRuntimeException() {
		super();
	}

	/**
	 * The constructor for AppRuntimeException.
	 * 
	 * @param cause Throwable
	 */
	public AppRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * The constructor for AppRuntimeException.
	 *
	 * @param message String
	 */
	public AppRuntimeException(String message) {
		super(message);
	}
}
