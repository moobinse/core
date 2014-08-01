package com.moobin.cache.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import com.dictiography.collections.IndexedTreeSet;
import com.moobin.cache.MMap;
import com.moobin.cache.MMapIndexListener;
import com.moobin.cache.MMapListener;
import com.moobin.cache.MMapSubscription;
import com.moobin.cache.SimpleCacheMap;
import com.moobin.core.MoobinException;

public class SortedCacheMap<T> implements MMap<T>, MMapListener<T> {
	
	private SimpleCacheMap<T> source;
	private Comparator<T> comparator;
	private final IndexedTreeSet<T> sortKeys;

	public SortedCacheMap(SimpleCacheMap<T> source, Comparator<T> comparator) {
		this.source = source;
		this.comparator = comparator;
		this.sortKeys = new IndexedTreeSet<T>(comparator);
		source.subscribe(this, true, 0);
	}

	@Override
	public String name() {
		return source.name() + ":" + comparator.toString();
	}

	@Override
	public Class<T> getType() {
		return source.getType();
	}

	@Override
	public T get(String key) {
		return source.get(key);
	}
 
	@Override
	public List<T> get(String... key) {
		return source.get(key);
	}

	@Override
	public String getKey(T item) {
		return source.getKey(item);
	}

	@Override
	public Function<T, String> getKeyFunction() {
		return source.getKeyFunction();
	}

	@Override
	public int size() {
		return source.size();
	}

	@Override
	public void add(T item) {
		throw new MoobinException(null);
	}

	@Override
	public T remove(String key) {
		throw new MoobinException(null);
	}

	@Override
	public MMapSubscription subscribe(MMapListener<T> listener,
			boolean withSnapshot, int delay) {
		return source.subscribe(listener, withSnapshot, delay);
	}

	@Override
	public int getIndex(T item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getByIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> get(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MMapSubscription subscribe(MMapIndexListener listener) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onEvent(T oldValue, T newValue) {
		// always called from source list
//		String key = getKey(item);
//		T oldValue = source.get(key);
//		int oldIndex = oldValue == null ? -1 : sortKeys.entryIndex(oldValue);
//		if (oldValue != null) {
//			sortKeys.remove(oldValue);
//		}
//		map.put(keyFunction.apply(newValue), newValue);
//		sortKeys.add(newValue);
//		int newIndex = sortKeys.entryIndex(newValue);
//		itemSubscriptions.forEach((s) -> s.listener.onEvent(oldValue, newValue));
//		rangeSubscriptions.forEach((r) -> r.listener.onEvent(oldIndex, newIndex));
	}
	
}
