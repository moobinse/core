package com.moobin.client.cache.impl;

import com.google.gwt.core.client.JavaScriptObject;

public class CacheResponse<T extends JavaScriptObject> {

	private T value;
	
	public T value() {
		return value;
	}
	
}
