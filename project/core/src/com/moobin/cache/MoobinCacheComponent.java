package com.moobin.cache;

public interface MoobinCacheComponent {

	void add(Object item);
	
	void remove(Object item);
	
	<T> T get(Class<T> type, Object key);
	
}
