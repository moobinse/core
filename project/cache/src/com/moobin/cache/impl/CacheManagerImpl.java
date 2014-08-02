package com.moobin.cache.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.moobin.cache.CacheManager;
import com.moobin.cache.CacheMap;
import com.moobin.cache.CacheRootMap;
import com.moobin.cache.CacheMapSorting;
import com.moobin.core.MoobinException;

public class CacheManagerImpl implements CacheManager {

	private Map<Class<?>, CacheRootMap<?>> rootMaps = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> CacheRootMap<T> getRootMap(Class<T> type) {
		return (CacheRootMap<T>) rootMaps.get(type);
	}

	@Override
	public <T> CacheRootMap<T> getRootMap(String name) {
		throw new MoobinException(null);
	}
	
	@Override
	public void createRootMap(Class<?> type) {
		CacheRootMap map = new CacheRootMapImpl<>(type);
		if (rootMaps.put(type, map) != null) {
			throw new MoobinException(null);
		}
	}

	@Override
	public <T> CacheRootMap<T> createRootMap(Class<T> type, String name) {
		throw new MoobinException(null);
	}

	@Override
	public <T> CacheMap<T> createFilteredMap(CacheMap<T> source, Predicate<T> filter) {
		return source.filter(filter);
	}


	@Override
	public <T> CacheMapSorting<T> createSortedMap(CacheMap<T> source,
			Comparator<T> sort) {
		return source.sort(sort);
	}
}
