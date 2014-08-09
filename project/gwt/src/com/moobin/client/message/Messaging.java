package com.moobin.client.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.shared.GWT;
import com.moobin.client.CacheCallback;
import com.moobin.client.cache.impl.CacheRequest;

public class Messaging {

	private static Messaging messaging = GWT.create(Messaging.class);

	private Callback<List<CacheRequest>, String> consumer;
	
	public static Messaging get() {
		return messaging;
	}
	
	private Map<Integer, CacheCallback<?>> callbacks = new HashMap<>();
	private int nextHandle;
	private List<CacheRequest> requests = new ArrayList<>();

	public Messaging() {
		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			@Override
			public boolean execute() {
				System.out.println(requests.size());
				if (consumer != null && !requests.isEmpty()) {
					consumer.onSuccess(requests);
					requests = new ArrayList<>();
					System.out.println("consumed");
				}
				return true;
			}
		}, 2000);
	}
	
	public void setConsumer(Callback<List<CacheRequest>, String> consumer) {
		this.consumer = consumer;
	}
	
	public void add(CacheRequest request, CacheCallback<?> callback) {
		Integer handle = nextHandle++;
		request.setHandle(handle);
		callbacks.put(handle, callback);
		requests.add(request);
	}
	
}
