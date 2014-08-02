package com.moobin.cache;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Magnus Lenti
 *
 * @param <T>
 */
public interface CacheMap<T> {

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
	 * 
	 * @return
	 */
	Stream<T> stream();
	
	/**
	 * @param listener
	 * @return
	 */
	HandlerRegistration subscribe(BiConsumer<T,T> listener);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	CacheMap<T> filter(Predicate<T> filter);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	CacheMapSorting<T> sort(Comparator<T> sort);

	/**
	 * 
	 * @param function
	 * @return
	 */
	default <U extends Comparable<? super U>> CacheMapSorting<T> sort(Function<T, U> function) {
		return sort(Comparator.comparing(function));
	}
	
}
