package com.moobin.core;

@SuppressWarnings("serial")
public class MoobinException extends RuntimeException {

	public MoobinException(Exception e) {
		super(e);
	}

	public MoobinException(String message) {
		super(message);
	}

}
