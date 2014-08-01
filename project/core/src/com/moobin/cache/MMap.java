package com.moobin.cache;

import java.util.List;

/**
 * @author Magnus Lenti
 *
 * @param <T>
 */
public interface MMap<T> extends SimpleCacheMap<T> {
	
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
	 * @param listener
	 * @return
	 */
	MMapSubscription subscribe(MMapIndexListener listener);
	
}
