package com.moobin.x.sim.configuration;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;

public class Sim {

	public static void main(String[] args) {

		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		
	}
}
