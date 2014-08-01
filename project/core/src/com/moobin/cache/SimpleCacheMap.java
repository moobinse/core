package com.moobin.cache;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Magnus Lenti
 *
 * @param <T>
 */
public interface SimpleCacheMap<T> {

	/**
	 * 
	 * @return
	 */
	String name();

	/**
	 * 
	 * @return
	 */
	Class<T> getType();

	/**
	 * @param key
	 * @return
	 */
	T get(String key);
	
	/**
	 * @param key
	 * @return
	 */
	List<T> get(String... key);

	/**
	 * @param item
	 * @return
	 */
	String getKey(T item);

	/**
	 * 
	 * @return
	 */
	Function<T, String> getKeyFunction();
	
	/**
	 * 
	 * @return
	 */
	int size();

	/**
	 * @param item
	 */
	void add(T item);
	
	/**
	 * @param items
	 */
	default void add(Stream<T> items) {
		items.forEach(this::add);
	}
	
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
	default void remove(Stream<T> items) {
		items.forEach(this::remove);
	}

	/**
	 * @param listener
	 * @param withSnapshot
	 * @param delay
	 * @return
	 */
	MMapSubscription subscribe(MMapListener<T> listener, boolean withSnapshot, int delay);

}
