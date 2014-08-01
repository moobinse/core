package com.moobin.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.moobin.cache.MMapListener;
import com.moobin.cache.MMapSubscription;
import com.moobin.cache.SimpleCacheMap;
import com.moobin.core.Core;
import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

@SuppressWarnings("unchecked")
public class SimpleCacheMapImpl<T> implements SimpleCacheMap<T> {

	private final Class<T> type;
	private final Map<String, T> map = new HashMap<String, T>();
	protected final MetaDataObject<T> meta;
	protected Function<T, String> keyFunction;
	private final Set<CacheSubscriptionImpl> itemSubscriptions = new HashSet<SimpleCacheMapImpl<T>.CacheSubscriptionImpl>();
	
	public SimpleCacheMapImpl(Class<T> type) {
		this.type = type;
		meta = Core.get().getMetaDataManager().getMetaData(type);
		MetaDataField<?, T> f = meta.getKeyField();
		this.keyFunction = (Function<T, String>) f.getFunction();
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
		String key = keyFunction.apply(newValue);
		T oldValue = map.put(key, newValue);
		itemSubscriptions.forEach((s) -> s.listener.onEvent(key, oldValue, newValue));
	}
	
	@Override
	public T remove(String key) {
		T oldValue = map.remove(key);
		if (oldValue != null) {
			itemSubscriptions.forEach((s) -> s.listener.onEvent(key, oldValue, null));
		}
		return oldValue;
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
	public int size() {
		return map.size();
	}

	@Override
	public MMapSubscription subscribe(MMapListener<T> listener, boolean withSnapshot, int delay) {
		CacheSubscriptionImpl subscription = new CacheSubscriptionImpl(listener, withSnapshot, delay);
		itemSubscriptions.add(subscription);
		return subscription;
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

	public void close() {
		if (itemSubscriptions.isEmpty()) {
			map.clear();
		}
		else {
			throw new MoobinException(null);
		}
	}

}
