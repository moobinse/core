package com.moobin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;

public class JsBase extends JavaScriptObject {

	protected native String get(String p) /*-{ return this[p]; }-*/;

    protected <T extends JsBase> void get(String type, String key, CacheCallback<T> callback) {
    	Moobin.getCache().get(type, key, callback);
	}
    
	protected native int getInteger(String p) /*-{ return this[p]; }-*/;
	
	protected native boolean getBoolean(String p) /*-{ return this[p]; }-*/;
	
	protected native <T extends JsBase> T getObject(String p) /*-{ return this[p]; }-*/;
	
	protected native JsArrayInteger getArrayInteger(String p) /*-{ return this[p]; }-*/;
	
	protected native JsArrayBoolean getArrayBoolean(String p) /*-{ return this[p]; }-*/;

	protected native JsArrayString getArrayString(String p) /*-{ return this[p]; }-*/;
	
	protected native <T extends JsBase> JsArray<T> getArray(String p) /*-{ return this[p]; }-*/;
	
	protected native void set(String p, String value) /*-{ this[p] = value; }-*/;
	
	protected native void set(String p, boolean value) /*-{ this[p] = value; }-*/;

	protected native void set(String p, int value) /*-{ this[p] = value; }-*/;
	
	protected native void set(String p, JavaScriptObject value) /*-{ this[p] = value; }-*/;

}
