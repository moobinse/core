package com.moobin.output.js;

import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

public class MoobinToJson extends MoobinTo {

	public MoobinToJson(Object item) {
		super(item);
	}
	
	@Override
	protected void startObject(MetaDataObject<?> meta) {
		buffer.append("{");
	}

	@Override
	protected void closeObject(MetaDataObject<?> meta) {
		buffer.append("\n}");
	}
	
	@Override
	protected void addNull() {
		buffer.append("null");
	}

	@Override
	protected void addSimple(Object value) {
		buffer.append(value);
	}
	
	@Override
	protected void addString(Object value) {
		buffer.append('"').append(value).append('"');
	}
	
	@Override
	protected void addFieldSeparator() {
		buffer.append(',');
	}

	@Override
	protected <T> void add(MetaDataField<?, T> field, T item) {
		add(field, field.getName(), field.get(item));
	}
	
	protected <T> void add(MetaDataField<?, T> field, String name, Object value) {
		buffer.append("\n  \"").append(name).append("\":");
		add(value);
	}

	@Override
	protected void addArray(Object arr) {
		buffer.append("[");
		if (arr instanceof int[]) add((int[]) arr);
		if (arr instanceof byte[]) add((byte[]) arr);
		if (arr instanceof Object[]) add((Object[]) arr);
		buffer.append("]");
	}
	
}
