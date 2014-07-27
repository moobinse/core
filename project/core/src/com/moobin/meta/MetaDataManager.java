package com.moobin.meta;

import java.lang.reflect.Field;

public interface MetaDataManager {

	<T> MetaDataObject<T> getMetaData(Class<T> type);
	
	<T> void add(Class<T> type);

	<T> MetaDataObject<T> createMetaDataObject(Class<T> clazz);
	
	<F,T> MetaDataField<F, T> createFieldMetaData(MetaDataObject<T> parent, Field field);

	@SuppressWarnings("unchecked")
	default <T> MetaDataObject<T> getItemMetaData(T item) {
		return getMetaData((Class<T>) item.getClass());
	}

	
}
