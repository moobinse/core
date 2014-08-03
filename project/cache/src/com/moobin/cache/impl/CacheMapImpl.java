package com.moobin.cache.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.moobin.cache.CacheMap;
import com.moobin.cache.CacheMapSorting;
import com.moobin.cache.HandlerRegistration;
import com.moobin.core.Core;
import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

@SuppressWarnings("unchecked")
public class CacheMapImpl<T> implements CacheMap<T> {

	private final Class<T> type;
	private final Map<String, T> map = new HashMap<String, T>();
	protected final MetaDataObject<T> meta;
	protected Function<T, String> keyFunction;
	private CacheCallbacks callbacks = new CacheCallbacks();
	private Subscriptions subscriptions = new Subscriptions();
	
	public CacheMapImpl(Class<T> type) {
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

	protected void add(T newValue) {
		String key = keyFunction.apply(newValue);
		T oldValue = map.put(key, newValue);
		subscriptions.onAdd(oldValue, newValue);
		callbacks.onAdd(newValue);
	}
	
	T remove(String key) {
		T oldValue = map.remove(key);
		if (oldValue != null) {
			subscriptions.onAdd(oldValue, null);
		}
		return oldValue;
	}
	
	/**
	 * Get a callback when an object satisfying the predicate is added
	 *  
	 * @param p
	 * @param c
	 */
	HandlerRegistration addCallback(Predicate<T> p, Consumer<T> c) {
		return callbacks.addCallback(p, c);
	}
	
	public HandlerRegistration subscribe(BiConsumer<T,T> listener) {
		return subscriptions.addSubscription(listener);
	}
	
	private class Subscriptions {
		
		private MoobinSet<BiConsumer<T, T>> listeners = new MoobinSet<>();
		
		public HandlerRegistration addSubscription(BiConsumer<T, T> listener) {
			listeners.add(listener);
			map.values().forEach((t) -> listener.accept(null, t));
			return () -> listeners.remove(listener);
		}
		
		void onAdd(T oldValue, T newValue) {
			listeners.forEach((c) -> c.accept(oldValue, newValue));
		}
		
	}
	
	public void close() {
		if (subscriptions.listeners.isEmpty()) {
			map.clear();
		}
		else {
			throw new MoobinException("");
		}
	}
	
	Stream<T> values() {
		return map.values().stream();
	}

	@Override
	public CacheMap<T> filter(Predicate<T> filter) {
		return new FilteredCacheMap<T>(this, filter);
	}

	@Override
	public CacheMapSorting<T> sort(Comparator<T> sort) {
		return new CacheMapSortingImpl<T>(this, sort);
	}

	public CacheMapSorting<T> sort(String field) {
		return sort(Comparator.comparing(meta.getComparableField(field).getFunction()));
	}

	public CacheMapSorting<T> sort(String... field) {
		Comparator<T> c = Comparator.comparing(meta.getComparableField(field[0]).getFunction());
		for (int i = 1; i < field.length; i++) {
			c = c.thenComparing(meta.getComparableField(field[i]).getFunction());
		}
		return sort(c);
	}

	public CacheMapSorting<T> sort(String field, boolean reversed) {
		return sort(Comparator
				.comparing(meta.getComparableField(field).getFunction())
				.reversed());
	}
	
	public CacheMapSorting<T> sort(String field1, String field2) {
		return sort(Comparator
				.comparing(meta.getComparableField(field1).getFunction())
				.thenComparing(meta.getComparableField(field2).getFunction()));
	}
	
	@Override
	public Stream<T> stream() {
		return map.values().stream();
	}
	
	private class CacheCallbacks {
		final MoobinMap<Predicate<T>, Consumer<T>> map = new MoobinMap<>();
		HandlerRegistration addCallback(Predicate<T> p, Consumer<T> c) {
			map.put(p, c);
			return () -> map.remove(p);
		}
		
		void onAdd(T item) {
			map.forEach((p,c) -> {
				if (p.test(item)) {
					c.accept(item);
					map.remove(p);
			}});
		}
	}
	
}
