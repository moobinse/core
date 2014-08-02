package com.moobin.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import com.moobin.core.MoobinException;

public class MoobinMap<K,T> {
	
	private final Map<K,T> map = new ConcurrentHashMap<>();
	
	public void put(K key, T item) {
		if (map.put(key, item) != null) {
			throw new MoobinException(null);
		}
	}
	
	public void remove(K key) {
		map.remove(key);
	}

	public void forEach(BiConsumer<K,T> action) {
		map.forEach(action);
	}
	
}
