package com.moobin.example.sim.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;
import com.moobin.client.CacheCallback;
import com.moobin.client.Moobin;
import com.moobin.client.message.PollServiceIf;
import com.moobin.example.sim.client.temp.MListBox;
import com.moobin.example.sim.client.temp.MMetaDataTable;
import com.moobin.example.sim.client.temp.MTable;
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
				System.out.println("got list with " + value.length() + " currencies");
				System.out.println(value.get(18).getName());
			}
		});
		
		JsCache.getCurrency("SEK", new CacheCallback<JsCurrency>() {
			public void callback(JsCurrency value) {
				System.out.println("got currency: " + value.getName());
			}
		});
		
		JsCache.getCountry("Sweden", new CacheCallback<JsCountry>() {
			@Override
			public void callback(JsCountry value) {
				System.out.println(value.toJson());
			}
		});
		NodeList<Element> list = RootPanel.getBodyElement().getElementsByTagName("DIV");
		for (int i = 0; i < list.getLength(); i++) {
			Element e = list.getItem(i);
			if (!e.getAttribute("_m").isEmpty()) {
				transform(e);
			}
		}
		RootPanel.get().add(MMetaDataTable.getButton());
		RootPanel.get().add(new MTable<>("Currency"));
		RootPanel.get().add(new MTable<>("Country"));
	}
	
	private void transform(Element e) {
		String type = e.getInnerText();
		final MListBox list = new MListBox(type);
		e.removeAllChildren();
		e.appendChild(list.getElement());
	}
	
	static native JavaScriptObject eval(String s) /*-{
		$wnd.eval("window._v = " + s);
		return $wnd._v;
	}-*/;
	
}
