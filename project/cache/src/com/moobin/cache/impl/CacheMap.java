package com.moobin.cache.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.dictiography.collections.IndexedTreeSet;
import com.moobin.cache.CacheCollection;

public class CacheMap<T> implements CacheCollection<T> {
	
	
	@FunctionalInterface public interface ItemListener<T> {
		void onEvent(T oldValue, T newValue);
	}
	
	@FunctionalInterface public interface RangeListener {
		void onEvent(int removeIndex, int addIndex);
	}

	private final Map<String, T> map = new HashMap<String, T>();
	private final IndexedTreeSet<T> sortKeys;
	private final List<ItemListener<T>> itemListeners = new ArrayList<ItemListener<T>>();
	private final List<RangeListener> rangeListeners = new ArrayList<RangeListener>();
 	private Predicate<T> filter;
	protected Function<T, String> keyFunction;
	private Comparator<T> keyCompare = (a, b) -> keyFunction.apply(a).compareTo(keyFunction.apply(b));
	
	public CacheMap(Predicate<T> filter, Function<T, String> keyFunction) {
		this();
		this.filter = filter;
		this.keyFunction = keyFunction;
	}
	
	public CacheMap(final Comparator<T> sortFunction) {
		Comparator<T> sort = (a,b) -> {
			int comp = sortFunction.compare(a, b);
			return comp != 0 ? comp : keyCompare.compare(a, b); 
		};
		sortKeys = new IndexedTreeSet<T>(sort);
	}

	public CacheMap() {
		sortKeys = new IndexedTreeSet<T>(keyCompare);
	}
	
	public void addRangeListener(RangeListener listener) {
		rangeListeners.add(listener);
	}
	
	public void addItemListener(ItemListener<T> listener) {
		itemListeners.add(listener);
	}
	
	public void remove(ItemListener<T> itemListener) {
		itemListeners.remove(itemListener);
	}

	public void remove(RangeListener rangeListener) {
		rangeListeners.remove(rangeListener);
	}
	
	public void add(T newValue) {
		if (filter.test(newValue)) {
			int oldIndex = -1;
			T oldValue = map.get(keyFunction.apply(newValue));
			if (oldValue != null) {
				oldIndex = sortKeys.entryIndex(oldValue);
				sortKeys.remove(oldValue);
			}
			map.put(keyFunction.apply(newValue), newValue);
			sortKeys.add(newValue);
			int newIndex = sortKeys.entryIndex(newValue);
			notify(oldValue, oldIndex, newValue, newIndex);
		}
		else {
			remove(newValue);
		}
	}
	
	void remove(T newValue) {
		T oldValue = map.remove(keyFunction.apply(newValue));
		if (oldValue != null) {
			int oldIndex = sortKeys.entryIndex(oldValue);
			sortKeys.remove(oldValue);
			notify(oldValue, oldIndex, null, -1);
		}
	}

	private void notify(T oldValue, int oldIndex, T newValue, int newIndex) {
		for (ItemListener<T> listener : itemListeners) {
			listener.onEvent(oldValue, newValue);
		}
		for (RangeListener listener : rangeListeners) {
			listener.onEvent(oldIndex, newIndex);
		}
	}

	@Override
	public List<T> get(int from, int to) {
		List<T> ret = new ArrayList<T>();
		for (int i = from; i < to; i++) {
			ret.add(sortKeys.exact(i));
		}
		return ret;
	}
	
	@Override
	public T get(int index) {
		return sortKeys.exact(index);
	}
	
	@Override
	public T get(String key) {
		return map.get(key);
	}
	
	@Override
	public List<T> get(String... keys) {
		List<T> ret = new ArrayList<T>();
		for (String key : keys) {
			ret.add(get(key));
		}
		return ret;
	}
	
	public int getIndex(T item) {
		return sortKeys.entryIndex(item);
	}
	
	public int size() {
		return map.size();
	}

}
