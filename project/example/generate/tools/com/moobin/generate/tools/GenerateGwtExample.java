package com.moobin.generate.tools;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.impl.MoobinConfigurationImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.tools.GenerateGwtTool;

public class GenerateGwtExample extends GenerateGwtTool {

	public static void main(String[] args) {

		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfigurationImpl(ExampleConfiguration.get()));
		Core.get().start();
		generateCacheNavigator("src");
		generateCacheItems("src");

	}

}
