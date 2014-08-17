package com.moobin.client;

import java.util.Collection;

public interface AsyncCache<B> {
	
	<T extends B> void get(String type, String key, CacheCallback<T> callback);

	<R> void getList(String type, Collection<String> keys, CacheCallback<R> callback);

	<R> void getList(String type, CacheCallback<R> callback);

	<T extends B> HandlerRegistration subscribe(String key, CacheSubscription<T> subscription);

}
