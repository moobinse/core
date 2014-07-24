package com.moobin.cache;

import java.util.List;

public interface CacheCollection<T> {

	int getIndex(T item);
	
	T get(int index);
	
	List<T> get(int from, int to);
	
	T get(String key);
	
	List<T> get(String... key);
	
}
