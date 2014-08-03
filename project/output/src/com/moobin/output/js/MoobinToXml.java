package com.moobin.output.js;

import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

public class MoobinToXml extends MoobinTo {

	public MoobinToXml(Object item) {
		super(item);
	}
	
	private MoobinToXml(Object item, StringBuffer buffer, int level) {
		super(item, buffer, level);
	}
	
	@Override
	protected void startObject(MetaDataObject<?> meta) {
		buffer.append("<").append(meta.getName()).append(">\n");
	}

	@Override
	protected void closeObject(MetaDataObject<?> meta) {
		buffer.append("\n</").append(meta.getName()).append('>');
	}
	
	@Override
	protected void addNull() {
		buffer.append("<null/>");
	}
	
	@Override
	protected void addSimple(Object value) {
		buffer.append(value);
	}
	
	@Override
	protected void addString(Object value) {
		buffer.append(value);
	}
	
	@Override
	protected void addFieldSeparator() {
		buffer.append("\n");
	}
	
	@Override
	protected <T> void add(MetaDataField<?, T> field, T item) {
		add(field, field.getName(), field.get(item));
	}
	
	private <T> void add(MetaDataField<?, T> field, String name, Object value) {
		buffer.append("  <").append(name).append('>');
		add(value);
		buffer.append("</").append(name).append('>');
	}

	@Override
	protected void addArray(Object arr) {
		if (arr instanceof int[]) add((int[]) arr);
		else if (arr instanceof byte[]) add((byte[]) arr);
		else if (arr instanceof Object[])
		{
			buffer.append("[");
			add((Object[]) arr);
			buffer.append("]");
		}
		else {
			throw new MoobinException("");
		}
	}

}
