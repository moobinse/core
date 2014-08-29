package com.moobin.client.view;

import com.google.gwt.core.client.Callback;
import com.moobin.client.CacheCallback;
import com.moobin.client.Moobin;
import com.moobin.generated.client.cache.JsMetaObject;

public class ViewDefinitions {

	public static void getViewDefinition(String objectType, final String viewType, final Callback<ViewDefinition, String> callback) {

		Moobin.getCache().get("MetaObject", objectType, new CacheCallback<JsMetaObject>() {
			@Override
			public void callback(JsMetaObject value) {
				callback.onSuccess(getViewDefinition(value, viewType));
			}
		});
		
	}

	protected static ViewDefinition getViewDefinition(JsMetaObject meta, String viewType) {
		ViewDefinition def = new ViewDefinition(meta);
		if (viewType.equals("table")) {
			
		}
		return def;
	}

}
