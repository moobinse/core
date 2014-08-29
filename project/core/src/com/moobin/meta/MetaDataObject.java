package com.moobin.meta;

import java.util.List;

public interface MetaDataObject<T> {
	
	String getName();

	Class<T> getType();

	MetaDataField<String, T> getKeyField();
	
	MetaDataField<String, T> getDisplayField();
	
	boolean isCacheRoot();
	
	<V> MetaDataField<V, T> getField(String field);

	default <V extends Comparable<V>> MetaDataField<V, T> getComparableField(String field) {
		return getField(field);
	}
	
	List<MetaDataField<?,T>> getFields();

	List<MetaDataField<?,T>> getSimpleFields();
	
	List<MetaDataField<?,T>> getSimpleArrayFields();
	
	List<MetaDataField<?,T>> getObjectFields();
	
	List<MetaDataField<?,T>> getObjectArrayFields();

	T create();

	void setCacheRoot(boolean b);

}
