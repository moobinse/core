package com.moobin.cache;

import java.util.Comparator;
import java.util.function.Predicate;

public interface CacheManager {

	<T> CacheRootMap<T> getRootMap(Class<T> type);

	<T> CacheRootMap<T> getRootMap(String name);

	<T> void createRootMap(Class<T> type);
	
	<T> CacheRootMap<T> createRootMap(Class<T> type, String name);
	
	<T> CacheMap<T> createFilteredMap(CacheMap<T> source, Predicate<T> filter);

	<T> CacheMapSorting<T> createSortedMap(CacheMap<T> source, Comparator<T> sort);

	default <T> void add(T item) {
		@SuppressWarnings("unchecked")
		CacheRootMap<T> map = getRootMap((Class<T>) item.getClass());
		if (map != null) {
			map.add(item);
		}
	}
	
	default <T> void remove(T item) {
		@SuppressWarnings("unchecked")
		CacheRootMap<T> map = getRootMap((Class<T>) item.getClass());
		if (map != null) {
			map.remove(item);
		}
	}
}
