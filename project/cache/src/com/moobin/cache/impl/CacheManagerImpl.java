package com.moobin.cache.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.moobin.cache.MMap;
import com.moobin.cache.MMapManager;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CacheManagerImpl implements MMapManager {

	private Map<Class<?>, MMap<?>> classMap = new HashMap();
	private Map<String, MMap<?>> stringMap = new HashMap();
	
	@Override
	public <T> MMap<T> get(Class<T> type) {
		return (MMap<T>) classMap.get(type);
	}

	@Override
	public <T> MMap<T> get(String name) {
		return (MMap<T>) stringMap.get(name);
	}
	
	public <T> MMap<T> createMapping(Class<T> type, Predicate<T> filter) {
		String key = type.getSimpleName() + "-" + filter.toString();
		MMap<T> filteredMap = (MMap<T>) stringMap.get(key);
		if (filteredMap == null) {
			MMap<T> source = createMapping(type);
			filteredMap = new CacheSubMap<T>(source, filter);
			stringMap.put(key, filteredMap);
		}
		return filteredMap;
	}

	@Override
	public <T> MMap<T> createMapping(Class<T> type) {
		MMap<T> map = get(type);
		if (map == null) {
			map = new CacheMapImpl<T>(type);
			classMap.put(type, map);
		}
		return map;
	}
}
