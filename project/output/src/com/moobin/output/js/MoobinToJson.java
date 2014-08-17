package com.moobin.output.js;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.moobin.core.Core;
import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;


public class MoobinToJson<T> {

	private T item;
	private MetaDataObject<T> meta;
	private JSONObject json;
	
	private MoobinToJson(T item) {
		this.item = item;
		meta = Core.get().getMetaDataManager().getItemMetaData(item);
		if (meta != null) {
			json = new JSONObject();
			meta.getFields().forEach(this::addField);
		}
	}

	private void addField(MetaDataField<?, T> field) {
		Object value = field.get(item);
		if (field.getJavaType() == Object.class && value != null && value.getClass().isArray()) {
			List<Object> list = new ArrayList<>();
			Arrays.asList((Object[]) value)
				.stream()
				.map((o) -> new MoobinToJson<>(o).json)
				.forEach(list::add);
			value = list;
		}
		else if (field.isArray()) {
			if (field.getType() == Type.OBJECT_ARRAY) {
				List<Object> list = new ArrayList<>();
				Arrays.asList((Object[]) value)
					.stream()
					.map((o) -> new MoobinToJson<>(o).json)
					.forEach(list::add);
				value = list;
			}
			else {
				//throw new MoobinException("not implemented");
			}
		}
		else  if (value != null && Core.get().getMetaDataManager().getItemMetaData(value) != null) {
			value = new MoobinToJson<>(value).json;
		}
		json.put(field.getName(), value);
	}
	
	@Override
	public String toString() {
		return json.toString();
	}
	
	public static String format(Object item) {
		return format(item, 0);
	}

	public static <I> String format(I item, int indent) {
		if (item == null) {
			return null;
		}
		MetaDataObject<I> meta = Core.get().getMetaDataManager().getItemMetaData(item);
		if (meta != null) {
			return new MoobinToJson<>(item).toString();
		}
		if (item.getClass().isArray()) {
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			int size = Array.getLength(item);
			for (int i = 0; i < size; i++) {
				if (i > 0) buf.append(',');
				buf.append(format(Array.get(item, i), indent));
			}
			buf.append("]");
			return buf.toString();
		}
		if (item instanceof Number || item instanceof Boolean) {
			return item.toString();
		}
		return '"' + item.toString() + '"';
	}
}
