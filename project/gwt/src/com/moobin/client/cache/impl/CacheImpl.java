package com.moobin.client.cache.impl;

import java.util.Collection;

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
		send(request, new CacheCallback<T>() {
			@Override
			public void callback(T result) {
				callback.callback(result);
			}
		});
	} 

	@Override
	public <R> void getList(String type, Collection<String> keys,
			final CacheCallback<R> callback) {
		CacheRequest request = CacheRequest.create(type, keys);
		send(request, new CacheCallback<R>() {
			@Override
			public void callback(R result) {
				if (result == null) {
					callback.callback(null);
				}
				else {
					callback.callback(result);
				}
			}
		}); 
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
	
	private <T> void send(CacheRequest request, CacheCallback<T> callback) {
		PollServiceIf.Singleton.get().add(request, callback);
	}
	
}
