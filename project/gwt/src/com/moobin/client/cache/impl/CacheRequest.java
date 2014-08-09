package com.moobin.client.cache.impl;

import java.util.Collection;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.moobin.client.JsBase;

public class CacheRequest extends JsBase {

	protected CacheRequest() {
		
	}
	
	public final void setHandle(int handle) {
		set("handle", handle);
	}
	
	public final int getHandle() {
		return getInteger("handle");
	}

	public static CacheRequest create(String type, String key) {
		CacheRequest req = JavaScriptObject.createObject().cast();
		req.set("type", type);
		req.set("key", key);
		return req;
	}

	public static CacheRequest create(String type, Collection<String> keys) {
		JsArrayString arr = JsArrayString.createArray().cast();
		for (String key : keys) {
			arr.push(key);
		}
		CacheRequest req = JavaScriptObject.createObject().cast();
		req.set("keys", arr);
		return req;
	}

}
