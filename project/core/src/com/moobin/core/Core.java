package com.moobin.core;

import com.moobin.cache.MMapManager;
import com.moobin.meta.MetaDataManager;


public interface Core {
	
	MetaDataManager getMetaDataManager();
	
	MMapManager getCacheManager();

	static class Managers {
		static Core core = new Core() {
			@Override
			public MetaDataManager getMetaDataManager() {
				return metaDataManager;
			}
			@Override
			public MMapManager getCacheManager() {
				return cacheManager;
			}
		};
		static MetaDataManager metaDataManager;
		static MMapManager cacheManager;
	}
	
	static Core get() {
		return Managers.core;
	}
	
	public static void set(Object manager) {
		if (manager instanceof MetaDataManager) {
			Managers.metaDataManager = (MetaDataManager) manager;
		}
		if (manager instanceof MMapManager) {
			Managers.cacheManager = (MMapManager) manager;
		}
	}
}
