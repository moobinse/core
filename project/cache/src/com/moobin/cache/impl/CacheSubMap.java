package com.moobin.cache.impl;

import java.util.function.Predicate;

public class CacheSubMap<T> extends CacheMap<T> {

	private ItemListener<T> itemListener = this::onChange;
	
	public CacheSubMap(CacheMap<T> source, Predicate<T> filter) {
		super(filter, source.keyFunction);
		source.addItemListener(itemListener);
	}
 	
	private void onChange(T oldValue, T newValue) {
		if (newValue == null) {
			remove(oldValue);
		}
		else {
			add(newValue);
		}
	}
	
}
