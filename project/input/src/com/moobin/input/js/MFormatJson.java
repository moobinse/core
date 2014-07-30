package com.moobin.input.js;

import com.moobin.meta.MetaDataField;

public class MFormatJson<T> extends MFormat<T> {

	public MFormatJson(T item) {
		super(item);
	}
	
	private MFormatJson(T item, MFormatJson<?> parent) {
		super(item, parent.builder, parent.level + 1);
	}

	@Override
	void openObject() {
		builder.append('{');
	}

	@Override
	void closeObject() {
		builder.append('}');
	}

	@Override
	void addSimple(MetaDataField<?, T> field) {
		builder.append(field.getName()).append(':');
		Object value = field.get(item);
		if (value == null) {
			builder.append("null");
		}
		else if (value instanceof Number) {
			builder.append(value.toString());
		}
		else {
			builder.append('"').append(value.toString()).append('"');
		}
	}

	@Override
	void addSimpleArray(MetaDataField<?, T> field) {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	void addObject(MetaDataField<?, T> field) {
		builder.append(field.getName()).append(':');
		Object value = field.get(item);
		if (value == null) {
			builder.append("null");
		}
		else {
			new MFormatJson(value, this).format();
		}
	}

	@Override
	void addObjectArray(MetaDataField<?, T> field) {
		
	}
	
	
}
