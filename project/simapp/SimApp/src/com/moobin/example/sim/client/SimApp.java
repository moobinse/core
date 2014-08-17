package com.moobin.example.sim.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.moobin.client.CacheCallback;
import com.moobin.client.message.PollServiceIf;
import com.moobin.generated.client.cache.JsCache;
import com.moobin.generated.client.cache.JsCountry;
import com.moobin.generated.client.cache.JsCurrency;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimApp implements EntryPoint {


	public void onModuleLoad() {
		PollServiceIf.Singleton.set(new PollService());
		
		JsCache.getCurrencyList(new CacheCallback<JsArray<JsCurrency>>() {
			public void callback(JsArray<JsCurrency> value) {
				System.out.println(value.length());
			}
		});
		
		JsCache.getCurrency("SEK", new CacheCallback<JsCurrency>() {
			public void callback(JsCurrency value) {
				System.out.println("got currency: " + value.getName());
			}
		});
		
		JsCache.getCountry("Norway", new CacheCallback<JsCountry>() {
			@Override
			public void callback(JsCountry value) {
				System.out.println("got country: " + value.getName());
			}
		});
	}
	
	static native JavaScriptObject eval(String s) /*-{
		$wnd.eval("window._v = " + s);
		return $wnd._v;
	}-*/;
	
}
