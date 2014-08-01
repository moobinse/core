package com.moobin.cache;

public interface MMapManager {

	<T> MMap<T> get(Class<T> type);

	<T> MMap<T> get(String name);

	default <T> T get(Class<T> type, String key) {
		return get(type).get(key);
	}
	
	@SuppressWarnings("unchecked")
	default <T> void add(T item) {
		get((Class<T>) item.getClass()).add(item);
	}
	
	@SuppressWarnings("unchecked")
	default <T> void remove(T item) {
		get((Class<T>) item.getClass()).remove(item);
	}

	<T> MMap<T> createMapping(Class<T> type);
	
}
