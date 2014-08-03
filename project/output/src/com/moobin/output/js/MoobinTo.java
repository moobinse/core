package com.moobin.output.js;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public abstract class MoobinTo {

	protected final StringBuffer buffer;
	protected final int level;

	protected MoobinTo(Object item) {
		this(item, new StringBuffer(), 0);
	}

	public MoobinTo(Object item, StringBuffer buffer, int level) {
		this.buffer = buffer;
		this.level = level;
	}

	protected <T> StringBuffer add(T item) {
		if (item == null) {
			addNull();
			return buffer;
		}
		if (item instanceof Number || item instanceof Boolean) {
			addSimple(item);
			return buffer;
		}
		if (item instanceof String) {
			addString(item);
			return buffer;
		}
		if (item instanceof Enum<?>) {
			addString(((Enum<?>) item).name()); 
			return buffer;
		}
		if (item.getClass().isArray()) {
			addArray(item);
			return buffer;
		}
		MetaDataObject<T> meta = Core.get().getMetaDataManager().getItemMetaData(item);
		if (meta == null) {
			return add("?");
		}
		startObject(meta);
		List<MetaDataField<?, T>> fields = meta.getFields();
		
		if (!fields.isEmpty()) {
			add(fields.get(0), item);
			fields.stream().skip(1).forEach(
				(f) -> { addFieldSeparator(); add(f, item); });
		}
		closeObject(meta);
		return buffer;
	}

	protected boolean isSimple(MetaDataField<?, ?> field) {
		return field.getType() == Type.SIMPLE || field.getJavaType() == int[].class;
	}

	protected abstract void startObject(MetaDataObject<?> meta);

	protected abstract void closeObject(MetaDataObject<?> meta);
	
	protected abstract void addNull();
	
	protected abstract void addSimple(Object value);
	
	protected abstract void addString(Object value);
	
	protected abstract void addFieldSeparator();
	
	protected abstract <T> void add(MetaDataField<?, T> field, T item);
	
	protected abstract void addArray(Object item);
	
	public final String toString() {
		return buffer.toString();
	}

	protected void add(Object[] arr) {
		if (arr.length > 0) {
			buffer.append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				buffer.append(',').append(arr[i]);
			}
		}
	}
	
	protected void add(int[] arr) {
		if (arr.length > 0) {
			buffer.append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				buffer.append(',').append(arr[i]);
			}
		}
	}

	protected void add(byte[] arr) {
		if (arr.length > 0) {
			buffer.append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				buffer.append(',').append(arr[i]);
			}
		}
	}
	
}
