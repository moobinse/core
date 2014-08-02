package com.moobin.meta.bt;

import com.moobin.annotation.bt.BtReference;
import com.moobin.meta.MetaDataField;

public class MetaReference<T> extends AbstractMetaBusinessType<T> {
	
	Class<?> referenceTo;
	
	
	public MetaReference(MetaDataField<T, ?> field, BtReference annotation) {
		super("Reference", field);
		referenceTo = annotation.value();
	}

	public Class<?> referenceTo() {
		return referenceTo;
	}
	
	@Override
	public String toString() {
		return name() + "[" + referenceTo.getSimpleName() + "]";
	}

}
