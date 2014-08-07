package com.moobin.client.cache.impl;

import java.util.Collection;

public class CacheRequest {

	public String type;
	
	public String key;
	
	public Collection<String> keys;
	
	public CacheRequest(String type, String key) {
		type = "item";
		this.key = key;
	}

	public CacheRequest(String type, Collection<String> keys) {
		type = "list";
		this.keys = keys;
	}

}
