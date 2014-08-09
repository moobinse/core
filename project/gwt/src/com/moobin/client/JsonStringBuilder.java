package com.moobin.client;

import com.google.gwt.core.client.JsArrayString;

public class JsonStringBuilder {

	private StringBuffer buffer;

	public static String toJson(Object o) {
		return new JsonStringBuilder(o).buffer.toString();
	}

	private JsonStringBuilder(Object o) {
		buffer = new StringBuffer();
		addObject(o);
	}

	private void add(Object o, String key) {
		if (isArray(o, key)) {
			addArray(o, key);
		} else if (isNumber(o, key) || isBoolean(o, key)) {
			buffer.append(getString(o, key));
		} else if (isString(o, key)) {
			buffer.append('"').append(getString(o, key)).append('"');
		} else {
			addObject(getObject(o, key));
		}
	}

	private void addArray(Object o, String key) {
		buffer.append("[");
		int size = arraySize(o, key);
		Object arr = getObject(o, key);
		for (int i = 0; i < size; i++) {
			if (i > 0)
				buffer.append(',');
			add(arr, i + "");
		}
		buffer.append("]");
	}

	private void addObject(Object o) {
		if (o == null) {
			buffer.append("null");
			return;
		}
		buffer.append('{');
		JsArrayString keys = keys(o);
		for (int i = 0; i < keys.length(); i++) {
			if (i > 0)
				buffer.append(',');
			buffer.append(keys.get(i)).append(':');
			add(o, keys.get(i));
		}
		buffer.append('}');
	}

	private static native String getString(Object o, String p) /*-{
		return o[p] + "";
	}-*/;

	private static native Object getObject(Object o, String p) /*-{
		return o[p];
	}-*/;

	private static native boolean isNumber(Object o, String p) /*-{
		return typeof o[p] == "number";
	}-*/;

	private static native boolean isString(Object o, String p) /*-{
		return typeof o[p] == "string";
	}-*/;

	private static native boolean isBoolean(Object o, String p) /*-{
		return typeof o[p] == "boolean";
	}-*/;

	private static native boolean isArray(Object o, String p) /*-{
		return Array.isArray(o[p]);
	}-*/;

	private static native int arraySize(Object o, String p) /*-{
		return o[p].length;
	}-*/;

	private static native JsArrayString keys(Object o) /*-{
		var arr = [];
		for (p in o)
			arr.push(p);
		return arr;
	}-*/;
}
