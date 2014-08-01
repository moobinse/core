package com.moobin.cache.impl;

import java.util.function.Predicate;

import com.moobin.cache.MMapListener;
import com.moobin.cache.MMapSubscription;
import com.moobin.cache.SimpleCacheMap;

public class FilteredCacheMap<T> extends SimpleCacheMapImpl<T> implements MMapListener<T> {

	private Predicate<T> filter;
	private final MMapSubscription subscription;

	public FilteredCacheMap(SimpleCacheMap<T> source, Predicate<T> filter) {
		super(source.getType());
		this.filter = filter;
		subscription = super.subscribe(this, true, 0);
	}
	
	@Override
	public String name() {
		return super.name() + "-" + filter.toString();
	}
	
	@Override
	public void add(T newValue) {
		if (filter.test(newValue)) {
			super.add(newValue);
		}
		else {
			remove(newValue);
		}
	}

	@Override
	public void onEvent(String key, T oldValue, T newValue) {
		if (newValue != null) {
			add(newValue);
		}
		else {
			remove(key);
		}
	}
	
	@Override
	public void close() {
		subscription.close();
		super.close();
	}
	
}
