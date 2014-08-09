package com.moobin.client;

import com.moobin.client.cache.impl.CacheImpl;

public class Moobin {

	private static AsyncCache<JsBase> moobinCache = new CacheImpl();
	
	public static AsyncCache<JsBase> getCache() {
		return moobinCache;
	}

}
