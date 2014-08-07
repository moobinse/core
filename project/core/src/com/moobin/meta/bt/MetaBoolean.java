package com.moobin.meta.bt;

import com.moobin.meta.MetaDataField;

public class MetaBoolean extends AbstractMetaBusinessType<Boolean> {

	public MetaBoolean(MetaDataField<Boolean, ?> field) {
		super("Boolean", field);
	}
	
	@Override
	public String toString() {
		return "Boolean";
	}

}
