package com.moobin.client.meta.impl;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.moobin.client.CacheCallback;
import com.moobin.client.Moobin;
import com.moobin.common.MCallback;
import com.moobin.common.MetaManagerIf;
import com.moobin.common.MetaObjectIf;
import com.moobin.generated.client.cache.JsMetaObject;


public class MetaManager implements MetaManagerIf {

	private final Map<String, MetaObjectIf> map = new HashMap<>();

	
	
	public void getMetaData(final String type, final MCallback<MetaObjectIf> callback) {
		if (map.containsKey(type)) {
			Scheduler.get().scheduleDeferred(new Command() {
				@Override
				public void execute() {
					callback.callback(map.get(type));
				}
			});
		}
		Moobin.getCache().get("MetaObject", type, new CacheCallback<JsMetaObject>() {
			@Override
			public void callback(JsMetaObject value) {
				MetaObjectIf meta = new MetaObjectImpl(value);
				map.put(type, meta);
				callback.callback(meta);
			}
		});
	}
	
}
