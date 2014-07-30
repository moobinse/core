package com.moobin.cache.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CacheMaps {
	
	private Map<Class<?>, CacheSubMaps<?>> cacheSubMaps = new HashMap<Class<?>, CacheSubMaps<?>>();
	
	public <T> CacheMap<T> get(Class<T> type) {
		return get(type, null);
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public <T> CacheMap<T> create(Class<T> type) {
		return createSubMaps(type).create(null);
	}
	
	<T> CacheMap<T> get(Class<T> type, Predicate<T> filter) {
		@SuppressWarnings("unchecked")
		CacheSubMaps<T> subMap = (CacheSubMaps<T>) cacheSubMaps.get(type);
		return subMap == null ? null : subMap.get(filter);
	}

	<T> CacheMap<T> create(Class<T> type, Predicate<T> filter) {
		return createSubMaps(type).create(filter);
	}
	
	private <T> CacheSubMaps<T> createSubMaps(Class<T> type) {
		@SuppressWarnings("unchecked")
		CacheSubMaps<T> subMap = (CacheSubMaps<T>) cacheSubMaps.get(type);
		if (subMap == null) {
			subMap = new CacheSubMaps<T>(type);
			cacheSubMaps.put(type, subMap);
		}
		return subMap;
	}
	
	private class CacheSubMaps<T> {
		
		private final Class<T> type;
		private final HashMap<String, CacheMap<T>> map = new HashMap<String, CacheMap<T>>();
		
		public CacheSubMaps(Class<T> type) {
			this.type = type;
		}
		
		private CacheMap<T> create(Predicate<T> filter) {
			CacheMap<T> subMap = get(filter.toString());
			if (subMap == null) {
				subMap = new CacheSubMap<T>(get(type), filter);
				map.put(filter.toString(), subMap);
			}
			return subMap;
		}

		public CacheMap<T> get(Object key) {
			return map.get(key);
		}
	}

}
