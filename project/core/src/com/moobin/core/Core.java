package com.moobin.core;

import com.moobin.cache.CacheManager;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.core.impl.CoreImpl;
import com.moobin.meta.MetaDataManager;


public interface Core {

	static Core core = new CoreImpl();

	MoobinConfiguration getConfiguration();
	
	MetaDataManager getMetaDataManager();
	
	CacheManager getCacheManager();

	<T> T set(T manager);

	void start();
	
	static Core get() {
		return core;
	}


}
