package com.moobin.client;

import java.util.Collection;
import java.util.List;

public interface AsyncCache<B> {
	
	<T extends B> void get(String type, String key, CacheCallback<T> callback);

	<T extends B> void getList(String type, Collection<String> keys, CacheCallback<List<T>> callback);

	<T extends B> void getList(String type, CacheCallback<List<T>> callback);

	<T extends B> HandlerRegistration subscribe(String key, CacheSubscription<T> subscription);

}
