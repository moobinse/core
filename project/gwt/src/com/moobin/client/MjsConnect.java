package com.moobin.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

public class MjsConnect {

	private Method m;

	public MjsConnect() {
		new RequestBuilder(m, "").setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				
			}
		});
	}
	
}
