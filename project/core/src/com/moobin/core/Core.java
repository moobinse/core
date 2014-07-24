package com.moobin.core;

import com.moobin.meta.MetaDataManager;


public interface Core {
	
	MetaDataManager getMetaDataManager();
	
	static Core get() {
		return null;
	}
}
