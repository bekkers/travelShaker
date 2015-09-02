package com.prochainvol;

@SuppressWarnings("serial")
public class ProchainvolRuntimeException extends RuntimeException {

	public ProchainvolRuntimeException() {
		super();
	}

	public ProchainvolRuntimeException(String message) {
		super(message);
	}

	public ProchainvolRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProchainvolRuntimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProchainvolRuntimeException(Throwable cause) {
		super(cause);
	}

}
