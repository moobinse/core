package com.moobin.example.sim.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import com.moobin.client.CacheCallback;
import com.moobin.client.JsonStringBuilder;
import com.moobin.client.cache.impl.CacheRequest;
import com.moobin.client.message.PollServiceIf;
import com.moobin.generated.client.cache.JsCacheResponse;
import com.moobin.generated.client.cache.JsPollResponse;

public class PollService implements PollServiceIf {

	private Map<Integer, CacheCallback<?>> callbacks = new HashMap<>();
	private int nextHandle;
	private List<CacheRequest> requests = new ArrayList<>();

	public PollService() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				if (!requests.isEmpty()) {
					send();
					requests = new ArrayList<>();
				}
				schedule(100);
			}
		};
		timer.schedule(100);
		
	}
	
	@Override
	public void add(CacheRequest request, CacheCallback<?> callback) {
		Integer handle = nextHandle++;
		request.setHandle(handle);
		callbacks.put(handle, callback);
		requests.add(request);
	}
	
	private void send() {
		JsArray<CacheRequest> arr = JavaScriptObject.createArray().cast();
		for (int i = 0; i < requests.size(); i++) {
			arr.set(i, requests.get(i));
		}
		System.out.println(JsonStringBuilder.toJson(arr));
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "/poll");
		try {
			builder.sendRequest(JsonStringBuilder.toJson(arr), new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					String text = response.getText();
					JsPollResponse rsp = eval(text).cast();
					JsArray<JsCacheResponse> crsp = rsp.getCacheResponses().cast();
					for (int i = 0; i < crsp.length(); i++) {
						try {
							response(crsp.get(i).getHandle(), crsp.get(i).getValue());
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
 				
				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println(request);
				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	<T> void response(int handle, T value) {
		@SuppressWarnings("unchecked")
		CacheCallback<T> cb = (CacheCallback<T>) callbacks.remove(handle);
		cb.callback((T) value);
	}

	static native JavaScriptObject eval(String s) /*-{
		$wnd.eval("window._v = " + s);
		return $wnd._v;
	}-*/;

}
