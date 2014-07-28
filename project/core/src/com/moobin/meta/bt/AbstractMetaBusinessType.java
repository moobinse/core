package com.moobin.meta.bt;

import com.moobin.meta.MetaBusinessType;
import com.moobin.meta.MetaDataField;

public abstract class AbstractMetaBusinessType<T> implements MetaBusinessType<T> {

	private final MetaDataField<T, ?> owner;
	private final String name;
	
	public AbstractMetaBusinessType(String name, MetaDataField<T, ?> field) {
		this.name = name;
		this.owner = field;
	}
	
	public final String name() {
		return name;
	}
	
	public final MetaDataField<T, ?> getOwner() {
		return owner;
	}

}
