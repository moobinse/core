package com.moobin.client.cache.impl;

import java.util.Collection;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerRegistration;
import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;
import com.moobin.client.Cache;
import com.moobin.client.CacheSubscription;

public class CacheImpl implements Cache {
	
	int nextHandle;

	@Override
	public <T extends JsBase> void get(String type, String key, CacheCallback<T> callback) {
		
		CacheRequest request = new CacheRequest(type, key);
		send(request, new CacheCallback<CacheResponse<T>>() {
			@Override
			public void callback(CacheResponse<T> result) {
				callback.callback(result.value());
			}
		});
	} 

	@Override
	public <T extends JsBase> void getList(String type, Collection<String> keys,
			CacheCallback<JsArray<T>> callback) {
		CacheRequest request = new CacheRequest(type, keys);
		send(request, new CacheCallback<CacheResponse<JsArray<T>>>() {
			@Override
			public void callback(CacheResponse<JsArray<T>> result) {
				callback.callback(result.value());
			}
		});
	}

	@Override
	public <T extends JsBase> void getList(String type, CacheCallback<JsArray<T>> callback) {
		getList(type, null, callback);
	}

	@Override
	public <T extends JsBase> HandlerRegistration subscribe(String key, CacheSubscription<T> subscription) {
		int handle = nextHandle++;
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				removeSubscription(handle);
			}
		};
	}
	
	private void removeSubscription(int handle) {
		
	}
	
	private <T extends JavaScriptObject> void send(CacheRequest request, CacheCallback<CacheResponse<T>> callback) {
		
	}
	
}
