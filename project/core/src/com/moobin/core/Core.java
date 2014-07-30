package com.moobin.core;

import com.moobin.cache.MoobinCacheManager;
import com.moobin.meta.MetaDataManager;


public interface Core {
	
	MetaDataManager getMetaDataManager();
	
	MoobinCacheManager getCacheManager();

	static class Managers {
		static Core core = new Core() {
			@Override
			public MetaDataManager getMetaDataManager() {
				return metaDataManager;
			}
			@Override
			public MoobinCacheManager getCacheManager() {
				return cacheManager;
			}
		};
		static MetaDataManager metaDataManager;
		static MoobinCacheManager cacheManager;
	}
	
	static Core get() {
		return Managers.core;
	}
	
	public static void set(Object manager) {
		if (manager instanceof MetaDataManager) {
			Managers.metaDataManager = (MetaDataManager) manager;
		}
		if (manager instanceof MoobinCacheManager) {
			Managers.cacheManager = (MoobinCacheManager) manager;
		}
	}
}
