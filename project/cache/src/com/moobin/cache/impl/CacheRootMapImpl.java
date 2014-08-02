package com.moobin.cache.impl;

import com.moobin.cache.CacheRootMap;

public class CacheRootMapImpl<T> extends CacheMapImpl<T> implements CacheRootMap<T> {

	public CacheRootMapImpl(Class<T> type) {
		super(type);
	}
	
	@Override
	public void add(T newValue) {
		super.add(newValue);
	}
	
	@Override
	public T remove(String key) {
		return super.remove(key);
	}

}
