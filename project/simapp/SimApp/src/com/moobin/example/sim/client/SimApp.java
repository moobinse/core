package com.moobin.example.sim.client;

import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.moobin.client.CacheCallback;
import com.moobin.client.JsonStringBuilder;
import com.moobin.client.cache.impl.CacheRequest;
import com.moobin.client.message.Messaging;
import com.moobin.generated.client.cache.JsCache;
import com.moobin.generated.client.cache.JsInOutTest;
import com.moobin.generated.client.cache.JsUser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimApp implements EntryPoint, Callback<List<CacheRequest>, String> {


	public void onModuleLoad() {
		JsCache.getUser("U1", new CacheCallback<JsUser>() {
			public void callback(JsUser value) {
				Window.alert(value.getUserId());
			}
		});
		
		JsInOutTest o = JavaScriptObject.createObject().cast();
		o.setCcy("SEK");
		o.setName("Moobin");
		o.setInts(1,99,8);
		o.setBoo(true);
		o.setNumber(123);
		JsInOutTest o2 = JavaScriptObject.createObject().cast();
		o2.setName("o2");
		o2.setChild(null);
		o.setChild(o2);
		System.out.println(JsonStringBuilder.toJson(o));
		System.out.println(JsonStringBuilder.toJson(test()));
		Messaging.get().setConsumer(this);
		testRequest();
	}
	
	private void testRequest() {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "/hello");

	    try {
	         Request response = builder.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					JsInOutTest t = eval(response.getText()).cast();
					Window.alert(t.getChild().getName());
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
			});
	    } catch (RequestException e) {
	        // ... doesn't matter for this example
	    }
	}
	
	static native JavaScriptObject eval(String s) /*-{
		$wnd.eval("window._v = " + s);
		return $wnd._v;
	}-*/;

	@Override
	public void onSuccess(List<CacheRequest> result) {
		System.out.println("consuming requests");
		for (CacheRequest request : result) {
			System.out.println(JsonStringBuilder.toJson(request));
		}
	}
	@Override
	public void onFailure(String reason) {
		// TODO Auto-generated method stub
		
	}
	
	private static native Object test() /*-{
		return {
			arr : [1, null, true, "TEST"],
			b : "boo"
		};
	}-*/;
	
}
