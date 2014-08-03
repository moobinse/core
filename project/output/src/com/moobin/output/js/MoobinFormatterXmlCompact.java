package com.moobin.output.js;

import java.util.List;

import com.moobin.meta.MetaDataField;

public class MoobinFormatterXmlCompact<T> extends MoobinFormatter<T> {

	private boolean hasChildren;

	public MoobinFormatterXmlCompact(T item, int level, StringBuffer buffer, String name) {
		super(item, level, buffer, name);
	}

	@Override
	protected void begin(String name) {
		indent();
		buffer.append('<').append(name);
	}

	@Override
	protected void end(String name) {
		if (hasChildren) {
			buffer.append('\n');
			indent();
			buffer.append("</").append(name).append('>');
		}
		else {
			buffer.append("/>");
		}
	}

	@Override
	protected void addSimple(MetaDataField<?, T> field) {
		buffer.append(' ').append(field.getName()).append("=\"");
		addSimpleValue(field.get(item));
		buffer.append('"');
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
		Object value = field.get(item);
		if (value != null) {
			if (!hasChildren) {
				hasChildren = true;
				buffer.append('>');
			}
			buffer.append('\n');
			new MoobinFormatterXmlCompact(value, level + 2, buffer, field.getName());
		}
	}

}
