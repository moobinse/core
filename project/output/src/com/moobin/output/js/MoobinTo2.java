package com.moobin.output.js;

import java.util.ArrayList;
import java.util.List;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;

public class MoobinTo2<T> {

	private final String name;
	private final T item;
	private final int level;
	private final MetaDataObject<T> meta;
	private final StringBuffer buffer;
	boolean firstField = true;

	public MoobinTo2(T item, int level, StringBuffer buffer) {
		this(item, level, buffer, item.getClass().getSimpleName());
	}

	public MoobinTo2(T item, int level, StringBuffer buffer, String name) {
		this.name = name;
		this.item = item;
		this.level = level;
		this.meta = Core.get().getMetaDataManager().getItemMetaData(item);
		this.buffer = buffer;
		begin();
		meta.getFields().stream().filter(this::isSimple).forEach(this::addSimple);
		meta.getFields().stream().filter((f) -> !isSimple(f)).forEach(this::addComplex);
		end();
	}
	
	protected boolean isSimple(MetaDataField<?,T> field) {
		return field.getType() == Type.SIMPLE || field.getJavaType().isPrimitive();
	}

	protected void begin() {
		buffer.append('{');
	}
	
	protected void end() {
		buffer.append('\n').append('}');
	}
	
	protected void indent() {
		for (int i = 0; i < 2 + level * 2; i++) {
			buffer.append(' ');
		}
	}
	
	protected void addSimple(MetaDataField<?,T> field) {
		onBeforeAddField();
		addSimpleName(field.getName());
		if (field.getType() == Type.SIMPLE_ARRAY) {
			addSimpleValue(primitiveArrayToList(field.get(item)));
		}
		else {
			addSimpleValue(field.get(item));
		}
	}

	protected void onBeforeAddField() {
		if (firstField) {
			firstField = false;
		}
		else {
			buffer.append(',');
		}
	}
	
	protected void addSimpleName(String name) {
		buffer.append("\n");
		indent();
		buffer.append(name).append(":");
	}
	
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
		else {
			buffer.append(value.toString());
		}
	}
	
	protected void addComplex(MetaDataField<?,T> field) {
		onBeforeAddField();
		addComplexName(field.getName());
		addComplexValue(field.get(item));
	}
	
	protected <C> void addComplexValue(C value) {
		if (value == null) {
			buffer.append("null");
		}
		else {
			new MoobinTo2<C> (value, level + 1, buffer);
		}
	}
	
	@Override
	public String toString() {
		return buffer.toString();
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
