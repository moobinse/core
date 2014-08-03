package com.moobin.output.js;

import java.util.List;

import com.moobin.meta.MetaDataField;

public class MoobinFormatterXml<T> extends MoobinFormatter<T> {

	public MoobinFormatterXml(T item, int level, StringBuffer buffer, String name) {
		super(item, level, buffer, name);
	}

	@Override
	protected void begin(String name) {
		indent();
		buffer.append('<').append(name).append('>');
	}

	@Override
	protected void end(String name) {
		buffer.append('\n');
		indent();
		buffer.append("</").append(name).append('>');
	}

	@Override
	protected void addSimple(MetaDataField<?, T> field) {
		buffer.append('\n');
		indent();
		buffer.append("  <").append(field.getName()).append('>');
		addSimpleValue(field.get(item));
		buffer.append("</").append(field.getName()).append('>');
	}

	protected void addSimpleValue(Object value) {
		if (value == null) {
			buffer.append("null");
		}
		else if (value.getClass().isArray()) {
			addPrimitiveArray(value);
		}
		else {
			buffer.append(value.toString());
		}
	}

	@Override
	protected void addSimpleName(String name) {
	}

	@Override
	protected void addComplex(MetaDataField<?, T> field) {
		if (field.get(item) != null) {
			buffer.append('\n');
			new MoobinFormatterXml(field.get(item), level + 2, buffer, field.getName());
		}
	}

}
