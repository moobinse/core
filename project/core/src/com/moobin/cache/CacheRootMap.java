package com.moobin.cache;

import java.util.stream.Stream;

public interface CacheRootMap<T> extends CacheMap<T> {

	/**
	 * @param item
	 */
	void add(T item);
	
	/**
	 * @param key
	 */
	T remove(String key);
	
	/**
	 * Remove item
	 * @param item
	 * @return returns true if item in the list
	 */
	default boolean remove(T item) {
		return remove(getKey(item)) != null;
	}
	
	/**
	 * @param items
	 */
	default void add(Stream<T> items) {
		items.forEach(this::add);
	}
	
	/**
	 * @param items
	 */
	default void remove(Stream<T> items) {
		items.forEach(this::remove);
	}
}
