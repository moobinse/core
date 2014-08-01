package com.moobin.cache.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CacheMaps {
	
	private Map<Class<?>, CacheSubMaps<?>> cacheSubMaps = new HashMap<Class<?>, CacheSubMaps<?>>();
	
	public <T> CacheMapImpl<T> get(Class<T> type) {
		return get(type, null);
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public <T> CacheMapImpl<T> create(Class<T> type) {
		return createSubMaps(type).get(null);
	}
	
	<T> CacheMapImpl<T> get(Class<T> type, Predicate<T> filter) {
		@SuppressWarnings("unchecked")
		CacheSubMaps<T> subMap = (CacheSubMaps<T>) cacheSubMaps.get(type);
		return subMap == null ? null : subMap.get(filter);
	}

	<T> CacheMapImpl<T> create(Class<T> type, Predicate<T> filter) {
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
		private final HashMap<String, CacheMapImpl<T>> map = new HashMap<String, CacheMapImpl<T>>();
		
		public CacheSubMaps(Class<T> type) {
			this.type = type;
			map.put(null, new CacheMapImpl<T>(type));
		}
		
		private CacheMapImpl<T> create(Predicate<T> filter) { 
			String key = filter.toString();
			CacheMapImpl<T> subMap = get(key);
			if (subMap == null) {
				subMap = new CacheSubMap<T>(get(type), filter);
				map.put(key, subMap);
			}
			return subMap;
		}

		public CacheMapImpl<T> get(Object key) {
			return map.get(key);
		}
	}

}
