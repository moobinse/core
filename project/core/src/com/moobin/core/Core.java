package com.moobin.core;

import com.moobin.meta.MetaDataManager;


public interface Core {
	
	MetaDataManager getMetaDataManager();

	static class Managers {
		static Core core = new Core() {
			@Override
			public MetaDataManager getMetaDataManager() {
				return metaDataManager;
			}
		};
		static MetaDataManager metaDataManager;
	}
	
	static Core get() {
		return Managers.core;
	}
	
	public static void set(Object manager) {
		if (manager instanceof MetaDataManager) {
			Managers.metaDataManager = (MetaDataManager) manager;
		}
	}
}
