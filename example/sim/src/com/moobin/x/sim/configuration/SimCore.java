package com.moobin.x.sim.configuration;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.impl.MoobinConfigurationImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;

public class SimCore {
	
	public static void init() {
		
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfigurationImpl(SimConfiguration.get()));
		Core.get().start();

	}

}
