package com.moobin.cache.impl;

import com.moobin.cache.impl.CacheMap.ItemListener;
import com.moobin.cache.impl.CacheMap.RangeListener;

public abstract class CacheRangeSubscription<T> {
	
	private CacheMap<T> source;
	private RangeListener rangeListener = this::handleRangeUpdate;
	private ItemListener<T> itemListener = this::handleItemUpdate;

	public CacheRangeSubscription(CacheMap<T> map) {
		source = map;
		map.addItemListener(itemListener);
		map.addRangeListener(rangeListener);
	}
	
	public CacheMap<T> getSource() {
		return source;
	}
	
	public void close() {
		source.remove(rangeListener);
		source.remove(itemListener);
	}

	protected abstract void handleItemUpdate(T oldValue, T newValue);
	
	protected abstract void handleRangeUpdate(int removeIndex, int insertIndex);
	
}
