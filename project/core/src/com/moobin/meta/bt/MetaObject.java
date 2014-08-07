package com.moobin.meta.bt;

import com.moobin.meta.MetaDataField;

public class MetaObject<T> extends AbstractMetaBusinessType<Class<T>> {

	public MetaObject(Class<T> type, MetaDataField<Class<T>, ?> field) {
		super(type.getSimpleName(), field);
	}
	
	@Override
	public String toString() {
		return "Object<" + name() + ">";
	}
}
