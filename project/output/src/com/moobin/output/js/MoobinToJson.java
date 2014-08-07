package com.moobin.output.js;

import org.json.JSONObject;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;


public class MoobinToJson<T> {

	private T item;
	private MetaDataObject<T> meta;
	private JSONObject json;
	
	private MoobinToJson(T item) {
		this.item = item;
		meta = Core.get().getMetaDataManager().getItemMetaData(item);
		json = new JSONObject();
		meta.getFields().forEach((f) -> addField(f));
	}

	private void addField(MetaDataField<?, T> field) {
		Object value = field.get(item);
		if (value != null && Core.get().getMetaDataManager().getItemMetaData(value) != null) {
			value = new MoobinToJson<>(value).json;
		}
		json.put(field.getName(), value);
	}
	
	public static <T2> String format(T2 item) {
		return new MoobinToJson<T2>(item).json.toString();
	}
}
