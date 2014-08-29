package com.moobin.core.impl;

import com.moobin.cache.CacheManager;
import com.moobin.configuration.ConfigurationManager;
import com.moobin.core.Core;
import com.moobin.core.MoobinException;
import com.moobin.core.data.MetaObject;
import com.moobin.feed.MoobinFeeder;
import com.moobin.meta.MetaDataManager;

public class CoreImpl implements Core {

	
	private ConfigurationManager configuration;
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
	public ConfigurationManager getConfiguration() {
		return configuration;
	}

	@Override
	public void start() {
		configuration.source().getAllTypes().forEach(metaDataManager::add);
		configuration.source().getAllCacheRoots().forEach((c) -> cacheManager.createRootMap(c));
		configuration.source().start();
		metaDataManager.getMetaData().stream().map(MetaObject::create).forEach(cacheManager::add);
		configuration.source().getAllFeeders().forEach(this::startFeed);
	}
	
	@Override
	public <T> T set(T manager) {
		if (manager instanceof MetaDataManager) {
			metaDataManager = (MetaDataManager) manager;
		}
		if (manager instanceof CacheManager) {
			cacheManager = (CacheManager) manager;
		}
		if (manager instanceof ConfigurationManager) {
			configuration = (ConfigurationManager) manager;
		}
		return manager;
	}
	
	private void startFeed(Class<? extends MoobinFeeder> clazz) {
		try {
			MoobinFeeder feeder = clazz.newInstance();
			feeder.start();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new MoobinException("Cannot instantiate feeder: " + clazz.getName());
		}
	}
	
}
