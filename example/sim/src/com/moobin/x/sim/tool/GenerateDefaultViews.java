package com.moobin.x.sim.tool;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.impl.MoobinConfigurationImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.tools.GenerateViewsTool;
import com.moobin.x.sim.configuration.SimConfiguration;

public class GenerateDefaultViews extends GenerateViewsTool {

	public static void main(String[] args) {

		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfigurationImpl(SimConfiguration.get()));
		Core.get().start();
		System.out.println(Core.get().getConfiguration().getName());
		generateDefaultViews("src");
	}

}
