package com.moobin.cache.impl;

import java.util.function.Predicate;

import com.moobin.cache.HandlerRegistration;
import com.moobin.cache.CacheMap;

public class FilteredCacheMap<T> extends CacheMapImpl<T> {

	private Predicate<T> filter;
	private final HandlerRegistration subscription;

	public FilteredCacheMap(CacheMap<T> source, Predicate<T> filter) {
		super(source.getType());
		this.filter = filter;
		subscription = source.subscribe(this::onEvent);
	}
	
	@Override
	public String name() {
		return super.name() + "-" + filter.toString();
	}
	
	@Override
	protected void add(T newValue) {
		if (filter.test(newValue)) {
			super.add(newValue);
		}
		else {
			remove(getKey(newValue));
		}
	}

	private void onEvent(T oldValue, T newValue) {
		if (newValue != null) {
			add(newValue);
		}
		else {
			remove(getKey(oldValue));
		}
	}
	
	@Override
	public void close() {
		subscription.remove();
		super.close();
	}
	
}
