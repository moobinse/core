package com.moobin.generate.tools;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.MoobinConfigurationSource;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.tools.GenerateGwtTool;

public class GenerateGwtExample extends GenerateGwtTool {
	
	public static void main(String[] args) {

		MoobinConfigurationSource config = new ExampleConfiguration();
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfiguration() {
			@Override
			public MoobinConfigurationSource source() {
				return config;
			}
		});
		Core.get().start();
		generateCacheNavigator("src");
		generateCacheItems("src");
		
	}
	
	
	
}
