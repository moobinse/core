package com.moobin.client.cache.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.moobin.client.AsyncCache;
import com.moobin.client.CacheCallback;
import com.moobin.client.CacheSubscription;
import com.moobin.client.HandlerRegistration;
import com.moobin.client.JsBase;
import com.moobin.client.message.PollServiceIf;

public class CacheImpl implements AsyncCache<JsBase> {
	
	int nextHandle;

	@Override
	public <T extends JsBase> void get(String type, String key, final CacheCallback<T> callback) {
		
		CacheRequest request = CacheRequest.create(type, key);
		send(request, new CacheCallback<CacheResponse<T>>() {
			@Override
			public void callback(CacheResponse<T> result) {
				callback.callback(result.getValue());
			}
		});
	} 

	@Override
	public <R> void getList(String type, Collection<String> keys,
			final CacheCallback<R> callback) {
		CacheRequest request = CacheRequest.create(type, keys);
		send(request, new CacheCallback<CacheResponse<R>>() {
			@Override
			public void callback(CacheResponse<R> result) {
				if (result == null) {
					callback.callback(null);
				}
				else {
					callback.callback(result.getValue());
				}
			}
		}); 
	}
	
	private static <T extends JsBase> List<T> makeList(JsArray<T> arr) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < arr.length(); i++) {
			list.add(arr.get(i));
		}
		return list;
	}

	@Override
	public <R> void getList(String type, final CacheCallback<R> callback) {
		getList(type, null, callback);
	}

	@Override
	public <T extends JsBase> HandlerRegistration subscribe(String key, CacheSubscription<T> subscription) {
		return new HandlerRegistration() {
			final int handle = nextHandle++;
			@Override
			public void remove() {
				removeSubscription(handle);
			}
		};
	}
	
	private void removeSubscription(int handle) {
		
	}
	
	private <T> void send(CacheRequest request, CacheCallback<CacheResponse<T>> callback) {
		PollServiceIf.Singleton.get().add(request, callback);
	}
	
}
