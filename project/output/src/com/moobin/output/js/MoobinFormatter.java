package com.moobin.output.js;

import java.util.ArrayList;
import java.util.List;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;

public abstract class MoobinFormatter<T> {

	protected final T item;
	protected final int level;
	private final MetaDataObject<T> meta;
	protected final StringBuffer buffer;

	public MoobinFormatter(T item, int level, StringBuffer buffer) {
		this(item, level, buffer, item.getClass().getSimpleName());
	}

	public MoobinFormatter(T item, int level, StringBuffer buffer, String name) {
		this.item = item;
		this.level = level;
		this.meta = Core.get().getMetaDataManager().getItemMetaData(item);
		this.buffer = buffer;
		if (name == null) name = meta.getName();
		begin(name);
		meta.getFields().stream().filter(this::isSimple).forEach(this::addSimple);
		meta.getFields().stream().filter((f) -> !isSimple(f)).forEach(this::addComplex);
		end(name);
	}
	
	protected boolean isSimple(MetaDataField<?,T> field) {
		return field.getType() == Type.SIMPLE || field.getJavaType().isPrimitive();
	}

	protected abstract void begin(String name);
	
	protected abstract void end(String name);
	
	protected void indent() {
		buffer.append("              ", 0, level * 2);
	}
	
	protected abstract void addSimple(MetaDataField<?, T> field);

	protected abstract void addSimpleName(String name);
	
	protected void addComplexName(String name) {
		addSimpleName(name);
	}
	
	protected void addSimpleValue(Object value) {
		if (value == null) {
			buffer.append("null");
		}
		else if (value instanceof String) {
			buffer.append('"').append(value).append('"');
		}
		else if (value.getClass().isArray()) {
			buffer.append('[');
			addPrimitiveArray(value);
			buffer.append(']');
		}
		else {
			buffer.append(value.toString());
		}
	}
	
	protected abstract void addComplex(MetaDataField<?,T> field);
	
	@Override
	public String toString() {
		return buffer.toString();
	}

	protected void addPrimitiveArray(Object arr) {
		List<Object> list = primitiveArrayToList(arr);
		if (!list.isEmpty()) {
			buffer.append(list.get(0));
			for (int i = 0; i < list.size(); i++) {
				buffer.append(',').append(list.get(i));
			}
		}
	}

	protected List<Object> primitiveArrayToList(Object arr) {
		List<Object> list = new ArrayList<>();
		if (arr instanceof short[]) {
			for (short i : (short[]) arr) list.add(i);
		}
		else if (arr instanceof char[]) {
			for (char i : (char[]) arr) list.add(i);
		}
		else if (arr instanceof byte[]) {
			for (byte i : (byte[]) arr) list.add(i);
		}
		else if (arr instanceof int[]) {
			for (int i : (int[]) arr) list.add(i);
		}
		else if (arr instanceof long[]) {
			for (long i : (long[]) arr) list.add(i);
		}
		else if (arr instanceof boolean[]) {
			for (boolean b : (boolean[]) arr) list.add(b);
		}
		return list;
	}
}
