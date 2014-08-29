package com.moobin.client;

import com.moobin.client.cache.impl.CacheImpl;
import com.moobin.client.meta.impl.MetaManager;
import com.moobin.client.view.ViewDefinitions;

public class Moobin {

	private static AsyncCache<JsBase> moobinCache = new CacheImpl();
	
	private static ViewDefinitions viewDefinitions = new ViewDefinitions();
	
	private static MetaManager metaManager = new MetaManager();
	
	public static AsyncCache<JsBase> getCache() {
		return moobinCache;
	}
	
	public static ViewDefinitions getViewDefinitions() {
		return viewDefinitions;
	}
	
	public static MetaManager getMetaManager() {
		return metaManager;
	}

}
