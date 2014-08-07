package com.moobin.client;

import java.util.Collection;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerRegistration;

public interface Cache {
	
	<T extends JsBase> void get(String type, String key, CacheCallback<T> callback);

	<T extends JsBase> void getList(String type, Collection<String> keys, CacheCallback<JsArray<T>> callback);

	<T extends JsBase> void getList(String type, CacheCallback<JsArray<T>> callback);

	<T extends JsBase> HandlerRegistration subscribe(String key, CacheSubscription<T> subscription);

}
