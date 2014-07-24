package com.moobin.cache;

import java.util.List;

public interface Cache<K,T> {

	T get(K key);

	T get(int pIndex);
	
	List<T> get(int pFrom, int pTo);
	
}
