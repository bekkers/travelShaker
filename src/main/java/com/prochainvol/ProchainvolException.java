package com.prochainvol;

@SuppressWarnings("serial")
public class ProchainvolException extends Exception {

	public ProchainvolException() {
		super();
	}

	public ProchainvolException(String message) {
		super(message);
	}

	public ProchainvolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProchainvolException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProchainvolException(Throwable cause) {
		super(cause);
	}

}
