package com.moobin.core.impl;

import com.moobin.cache.CacheManager;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManager;

public class CoreImpl implements Core {

	
	private MoobinConfiguration configuration;
	private MetaDataManager metaDataManager;
	private CacheManager cacheManager;

	@Override
	public MetaDataManager getMetaDataManager() {
		return metaDataManager;
	}
	@Override
	public CacheManager getCacheManager() {
		return cacheManager;
	}
	
	@Override
	public MoobinConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	public void start() {
		configuration.source().getAllTypes().forEach(metaDataManager::add);
		configuration.source().getCacheRoots().forEach((c) -> cacheManager.createRootMap(c));
		configuration.source().start();
	}
	
	@Override
	public <T> T set(T manager) {
		if (manager instanceof MetaDataManager) {
			metaDataManager = (MetaDataManager) manager;
		}
		if (manager instanceof CacheManager) {
			cacheManager = (CacheManager) manager;
		}
		if (manager instanceof MoobinConfiguration) {
			configuration = (MoobinConfiguration) manager;
		}
		return manager;
	}
	
}
