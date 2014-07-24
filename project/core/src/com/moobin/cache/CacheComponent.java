package com.moobin.cache;

public interface CacheComponent {

	<T> Cache<?, T> getCache(Class<T> itemClass);
	
}
