package com.moobin.input.js;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
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
		meta.getSimpleFields().forEach(this::addSimple);
		meta.getObjectFields().forEach(this::addObject);
		meta.getSimpleArrayFields().forEach(this::addSimpleArray);
		meta.getObjectArrayFields().forEach(this::addObjectArray);
		closeObject();
	}
	
	abstract void addSimple(MetaDataField<?, T> field);

	abstract void addSimpleArray(MetaDataField<?, T> field);
	
	abstract void addObject(MetaDataField<?, T> field);
	
	abstract void addObjectArray(MetaDataField<?, T> field);
	
	abstract void openObject();
	
	abstract void closeObject();

}
