package com.moobin.core.data;

import com.moobin.meta.MetaDataField;

public class MetaField {

	
	public String name;
	
	public String businessType;
	
	public String javaType;

	public static MetaField create(MetaDataField<?, ?> metaDataField) {
		MetaField f = new MetaField();
		f.name = metaDataField.getName();
		f.businessType = metaDataField.getType().toString();
		f.javaType = metaDataField.getJavaType().getSimpleName();
		return f;
	}
	
}
