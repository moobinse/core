package com.moobin.meta.bt;

import java.util.Arrays;

import com.moobin.meta.MetaDataField;

public class MetaEnum<E extends Enum<E>> extends AbstractMetaBusinessType<E> {

	private final String enumType;
	
	public MetaEnum(MetaDataField<E, ?> field) {
		super("Enum", field);
		enumType = field.getJavaType().getName();
		field.getJavaType().getEnumConstants();
	}
	
	public String getEnumType() {
		return enumType;
	}
	
	public E[] getValues() {
		return getOwner().getJavaType().getEnumConstants();
	}
	
	@Override
	public String toString() {
		return "Enum:" + enumType + Arrays.asList(getValues());
	}
	
}
