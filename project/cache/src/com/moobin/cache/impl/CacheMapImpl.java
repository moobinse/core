package com.moobin.cache.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import com.dictiography.collections.IndexedTreeSet;
import com.moobin.cache.MMap;
import com.moobin.cache.MMapSubscription;
import com.moobin.cache.MMapListener;
import com.moobin.cache.MMapIndexListener;
import com.moobin.core.Core;
import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

@SuppressWarnings("unchecked")
public class CacheMapImpl<T> implements MMap<T> {
	
	private final Class<T> type;
	private final Map<String, T> map = new HashMap<String, T>();
	private final IndexedTreeSet<T> sortKeys;
 	private Predicate<T> filter;
	protected Function<T, String> keyFunction;
	private Comparator<T> keyCompare = (a, b) -> keyFunction.apply(a).compareTo(keyFunction.apply(b));
	private final Set<CacheSubscriptionImpl> itemSubscriptions = new HashSet();
	private final Set<RangeSubscriptionImpl> rangeSubscriptions = new HashSet();
	
//	public CacheMapImpl(Predicate<T> filter, Function<T, String> keyFunction) {
//		this.filter = filter;
//		this.keyFunction = keyFunction;
//		keyCompare = new Comparator<T>() {
//			@Override
//			public int compare(T o1, T o2) {
//				return keyFunction.apply(o1).compareTo(keyFunction.apply(o2));
//			}
//		};
//		sortKeys = new IndexedTreeSet<T>(keyCompare);
//	}

	public CacheMapImpl(Class<T> type) {
		this.type = type;
		MetaDataObject<T> meta = Core.get().getMetaDataManager().getMetaData(type);
		MetaDataField<?, T> f = meta.getKeyField();
		this.filter = (i) -> true;
		this.keyFunction = (Function<T, String>) f.getFunction();
		keyCompare = new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return keyFunction.apply(o1).compareTo(keyFunction.apply(o2));
			}
		};
		sortKeys = new IndexedTreeSet<T>(keyCompare);
	}
	
	@Override
	public String name() {
		return type.getSimpleName();
	}
	
	@Override
	public Class<T> getType() {
		return type;
	}
	
	public void add(T newValue) {
		if (filter == null || filter.test(newValue)) {
			String key = keyFunction.apply(newValue);
			T oldValue = map.get(key);
			int oldIndex = oldValue == null ? -1 : sortKeys.entryIndex(oldValue);
			if (oldValue != null) {
				sortKeys.remove(oldValue);
			}
			map.put(keyFunction.apply(newValue), newValue);
			sortKeys.add(newValue);
			int newIndex = sortKeys.entryIndex(newValue);
			itemSubscriptions.forEach((s) -> s.listener.onEvent(key, oldValue, newValue));
			rangeSubscriptions.forEach((r) -> r.listener.onEvent(oldIndex, newIndex));
		}
		else {
			remove(newValue);
		}
	}
	
	@Override
	public boolean remove(T newValue) {
		return remove(keyFunction.apply(newValue)) != null;
	}
	
	@Override
	public T remove(String key) {
		T oldValue = map.remove(key);
		if (oldValue != null) {
			int oldIndex = sortKeys.entryIndex(oldValue);
			sortKeys.remove(oldValue);
			itemSubscriptions.forEach((s) -> s.listener.onEvent(key, oldValue, null));
			rangeSubscriptions.forEach((r) -> r.listener.onEvent(oldIndex, -1));
		}
		return oldValue;
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
	public T getByIndex(int index) {
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
	
	@Override
	public String getKey(T item) {
		return keyFunction.apply(item);
	}
	
	@Override
	public Function<T, String> getKeyFunction() {
		return keyFunction;
	}

	@Override
	public int getIndex(T item) {
		return sortKeys.entryIndex(item);
	}
	
	@Override
	public int size() {
		return map.size();
	}

	@Override
	public MMapSubscription subscribe(MMapListener<T> listener, boolean withSnapshot, int delay) {
		CacheSubscriptionImpl subscription = new CacheSubscriptionImpl(listener, withSnapshot, delay);
		itemSubscriptions.add(subscription);
		return subscription;
	}
	
	@Override
	public MMapSubscription subscribe(MMapIndexListener listener) {
		RangeSubscriptionImpl subscription = new RangeSubscriptionImpl(listener);
		rangeSubscriptions.add(subscription);
		return subscription;
	}
	
//	private void notify(T oldValue, int oldIndex, T newValue, int newIndex) {
//		itemSubscriptions.forEach((s) -> s.listener.onEvent(oldValue, newValue));
//		rangeSubscriptions.forEach((r) -> r.listener.onEvent(oldIndex, newIndex));
//	}

	private class RangeSubscriptionImpl implements MMapSubscription {

		private MMapIndexListener listener;
		
		private RangeSubscriptionImpl(MMapIndexListener listener) {
			this.listener = listener;
		}

		@Override
		public void close() {
			rangeSubscriptions.remove(this);
			listener = null;
		}
	}
	
	private class CacheSubscriptionImpl implements MMapSubscription {

		private MMapListener<T> listener;

		private CacheSubscriptionImpl(MMapListener<T> listener, boolean withSnapshot, int delay) {
			this.listener = listener;
			if (withSnapshot) {
				new ArrayList<T>(map.values()).stream().forEach((t) -> listener.onEvent(getKey(t), null, t));
			}
		}

		@Override
		public void close() {
			itemSubscriptions.remove(this);
			listener = null;
		}

	}

	public void destroy() {
		if (itemSubscriptions.isEmpty() && rangeSubscriptions.isEmpty()) {
			map.clear();
			sortKeys.clear();
		}
		else {
			throw new MoobinException(null);
		}
	}

}
