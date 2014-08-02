package com.moobin.cache;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * @author Magnus Lenti
 *
 * @param <T>
 */
public interface CacheMapSorting<T> {

	/**
	 * 
	 * @return
	 */
	CacheMap<T> source();
	
	/**
	 * @param item
	 * @return
	 */
	int getIndex(T item);

	/**
	 * @param index
	 * @return
	 */
	T getByIndex(int index);
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	List<T> get(int from, int to);
	
	/**
	 * 
	 * @return
	 */
	Stream<T> stream();
	/**
	 * @param listener
	 * @return
	 */
	HandlerRegistration subscribeOnIndex(BiConsumer<Integer, Integer> listener);
	
}
