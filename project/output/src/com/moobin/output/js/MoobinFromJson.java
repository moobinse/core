package com.moobin.output.js;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;


public class MoobinFromJson<T> {

	private T item;
	private MetaDataObject<T> meta;
	private JSONObject json;
	
	private MoobinFromJson(Class<T> javaType, JSONObject value) {
		json = value;
		meta = Core.get().getMetaDataManager().getMetaData(javaType);
		item = meta.create();
		json.keys().forEachRemaining((f) -> addField(meta.getField(f), json.get(f)));
	}

	private void addField(MetaDataField<?, T> field, Object value) {
		if (field != null) {
			value = normalize(value, field.getJavaType());
			field.set(item, value);
		}
	}
	
	private Object normalize(Object value, Class<?> type) {
		if (value == JSONObject.NULL) {
			return null;
		}
		else if (value instanceof JSONArray) {
			return  jsonArrayToList((JSONArray) value, type);
		}
		if (value instanceof JSONObject) {
			return  parse(type, (JSONObject) value);
		}
		return value;
	}
	
	private List<Object> jsonArrayToList(JSONArray array, Class<?> type) {
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			list.add(parse(type, (JSONObject) array.get(i)));
		}
		return list;
	}

	public static <T> T parse(Class<T> clazz, JSONObject json) {
		return new MoobinFromJson<T>(clazz, json).item;
	}
	
	public static <T> T parse(Class<T> clazz, String in) {
		return new MoobinFromJson<T>(clazz, new JSONObject(in)).item;
	}
}
