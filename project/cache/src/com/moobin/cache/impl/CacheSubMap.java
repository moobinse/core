package com.moobin.cache.impl;

import java.util.function.Predicate;

import com.moobin.cache.MMap;
import com.moobin.cache.MMapListener;
import com.moobin.cache.MMapSubscription;

public class CacheSubMap<T> extends CacheMapImpl<T> {

	private MMapListener<T> itemListener = this::onChange;
	private MMapSubscription subscription;
	
	public CacheSubMap(MMap<T> source, Predicate<T> filter) {
		super(filter, source.getKeyFunction());
		subscription = source.subscribe(itemListener, true, 0);
	}
	
	private void onChange(T oldValue, T newValue) {
		if (newValue == null) {
			remove(oldValue);
		}
		else {
			add(newValue);
		}
	}
	
	public void destroy() {
		subscription.close();
		super.destroy();
	}
	
}
