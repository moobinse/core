package com.moobin.output.js;

import com.moobin.meta.MetaDataField;

public class MoobinFormatterJson<T> extends MoobinFormatter<T> {

	boolean first = false;
	
	public MoobinFormatterJson(T item, int level, StringBuffer buffer) {
		super(item, level, buffer);
	}

	@Override
	protected void begin(String name) {
		indent();
		buffer.append('{');
	}

	@Override
	protected void end(String name) {
		buffer.append('\n');
		indent();
		buffer.append('}');
	}

	@Override
	protected void addSimple(MetaDataField<?, T> field) {
		addName(field.getName());
		addSimpleValue(field.get(item));
	}

	@Override
	protected void addSimpleName(String name) {
	}

	@Override
	protected void addComplex(MetaDataField<?, T> field) {
		addName(field.getName());
		if (field.get(item) == null) {
			buffer.append("null");
		}
		else {
			new MoobinFormatterJson(field.get(item), level + 1, buffer);
		}
	}
	
	private void addName(String name) {
		if (!first) {
			first = true;
		}
		else {
			buffer.append(',');
		}
		buffer.append('\n');
		indent();
		buffer.append("  ");
		buffer.append(name).append(':');
	}

}
