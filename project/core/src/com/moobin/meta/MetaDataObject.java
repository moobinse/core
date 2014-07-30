package com.moobin.meta;

import java.util.List;
import java.util.function.Function;

public interface MetaDataObject<T> {
	
	String getName();

	MetaDataField<?, T> getKeyField();
	
	MetaDataField<String, T> getDisplayField();
	
	Function<T, ?> keyFunction();

	Function<T, String> displayFunction();

	MetaDataField<?, T> getField(String field);
	
	List<MetaDataField<?,T>> getFields();

	List<MetaDataField<?,T>> getSimpleFields();
	
	List<MetaDataField<?,T>> getSimpleArrayFields();
	
	List<MetaDataField<?,T>> getObjectFields();
	
	List<MetaDataField<?,T>> getObjectArrayFields();

	T create();
	
}
