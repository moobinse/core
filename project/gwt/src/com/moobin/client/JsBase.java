package com.moobin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;

public class JsBase extends JavaScriptObject {
	
	protected JsBase() {
		
	}
	
	protected final native boolean isNull(String p) /*-{
		return this[p] == null;
	}-*/;

	public final native String get(String p) /*-{ return this[p]; }-*/;

    protected final <T extends JsBase> void get(String type, String key, CacheCallback<T> callback) {
    	Moobin.getCache().get(type, key, callback);
	}
    
	protected final native int getInteger(String p) /*-{ return this[p]; }-*/;
	
	protected final native boolean getBoolean(String p) /*-{ return this[p]; }-*/;
	
	protected final native <T extends JsBase> T getObject(String p) /*-{ return this[p]; }-*/;
	
	protected final native JsArrayInteger getArrayInteger(String p) /*-{ return this[p]; }-*/;
	
	protected final native JsArrayBoolean getArrayBoolean(String p) /*-{ return this[p]; }-*/;

	protected final native JsArrayString getArrayString(String p) /*-{ return this[p]; }-*/;
	
	protected final native <T extends JsBase> JsArray<T> getArray(String p) /*-{ return this[p]; }-*/;
	
	protected final native void set(String p, String value) /*-{ this[p] = value; }-*/;
	
	protected final native void set(String p, boolean value) /*-{ this[p] = value; }-*/;

	protected final native void set(String p, int value) /*-{ this[p] = value; }-*/;
	
	protected final native void set(String p, JavaScriptObject value) /*-{ this[p] = value; }-*/;

	public final String toJson() {
		return _toJson(this);
	}

	private static native JsArrayString _keys(JsBase o) /*-{
		var arr = [];
		for (p in o) {
			arr.push(p);
		}
		return arr;
	}-*/;
	
	private static native String _toJson(JsBase o) /*-{
		s = "{";
		for (var p in o) {
			s = s + p + " " + o[p] + ",";
		}
		return s + "}";
	}-*/;
	
}
