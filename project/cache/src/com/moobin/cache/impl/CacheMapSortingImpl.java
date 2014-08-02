package com.moobin.cache.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import com.dictiography.collections.IndexedTreeSet;
import com.moobin.cache.CacheMap;
import com.moobin.cache.CacheMapSorting;
import com.moobin.cache.HandlerRegistration;

public class CacheMapSortingImpl<T> implements CacheMapSorting<T> {
	
	private CacheMap<T> source;
	private final IndexedTreeSet<T> treeSet;
	private final IndexSubscriptions subscriptions = new IndexSubscriptions();

	public CacheMapSortingImpl(CacheMap<T> source, Comparator<T> comparator) {
		this.source = source;
		this.treeSet = new IndexedTreeSet<T>(comparator);
		source.subscribe(this::onEvent);
	}

	@Override
	public CacheMap<T> source() {
		return source;
	}
	
	@Override
	public int getIndex(T item) {
		return treeSet.entryIndex(item);
	}

	@Override
	public T getByIndex(int index) {
		return treeSet.exact(index);
	}

	@Override
	public List<T> get(int from, int to) {
		List<T> list = new ArrayList<>();
		for (int i = from; i <= to; i++) {
			list.add(treeSet.exact(i));
		}
		return list;
	}

	private void onEvent(T oldValue, T newValue) {
		Integer oldIndex = null;
		Integer newIndex = null;
		if (oldValue != null) {
			oldIndex = treeSet.entryIndex(oldValue);
			treeSet.remove(oldValue);
		}
		if (newValue != null) {
			treeSet.add(newValue);
			newIndex = treeSet.entryIndex(newValue);
		}
		subscriptions.onEvent(oldIndex, newIndex);
	}
	
	@Override
	public HandlerRegistration subscribeOnIndex(BiConsumer<Integer, Integer> listener) {
		return subscriptions.add(listener);
	}
	
	@Override
	public Stream<T> stream() {
		return treeSet.stream();
	}
	
	private class IndexSubscriptions {

		MoobinSet<BiConsumer<Integer, Integer>> listeners = new MoobinSet<>();
		
		void onEvent(Integer oldIndex, Integer newIndex) {
			listeners.forEach((c) -> c.accept(oldIndex, newIndex));
		}

		HandlerRegistration add(BiConsumer<Integer, Integer> listener) {
			listeners.add(listener);
			return () -> listeners.remove(listener);
		}
		
	}
	
}
