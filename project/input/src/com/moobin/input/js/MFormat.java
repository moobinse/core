package com.moobin.input.js;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;

public abstract class MFormat<T> {
	
	protected final T item;
	protected final int level;
	protected final StringBuilder builder;
	protected final MetaDataObject<T> meta;
	
	public MFormat(T item, StringBuilder builder, int level) {
		this.item = item;
		this.level = level;
		this.builder = builder;
		meta = Core.get().getMetaDataManager().getItemMetaData(item);
	}
	
	public MFormat(T item) {
		this(item, new StringBuilder(), 0);
	}
	
	protected void format() {
		openObject();
		meta.getFields((f) -> f.getType() == Type.SIMPLE).forEach(this::addSimple);
		meta.getFields((f) -> f.getType() == Type.OBJECT).forEach(this::addObject);
		meta.getFields((f) -> f.getType() == Type.SIMPLE_ARRAY).forEach(this::addSimpleArray);
		meta.getFields((f) -> f.getType() == Type.OBJECT_ARRAY).forEach(this::addObjectArray);
		closeObject();
	}
	
	abstract void addSimple(MetaDataField<?, T> field);

	abstract void addSimpleArray(MetaDataField<?, T> field);
	
	abstract void addObject(MetaDataField<?, T> field);
	
	abstract void addObjectArray(MetaDataField<?, T> field);
	
	abstract void openObject();
	
	abstract void closeObject();

}
