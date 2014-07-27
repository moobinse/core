package com.moobin.meta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class MetaDataManagerImpl implements MetaDataManager {

	private Map<Class<?>, MetaDataObject<?>> classMap = new HashMap<Class<?>, MetaDataObject<?>>();
	private Map<String, MetaDataObject<?>> nameMap = new HashMap<String, MetaDataObject<?>>();
	private Function<Class<?>, MetaDataObject> metaDataObjectConstructor = MetaDataObjectImpl::new;
	private BiFunction<MetaDataObject, Field, MetaDataField> metaDataFieldConstructor = MetaDataFieldImpl::new;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> MetaDataObject<T> getMetaData(Class<T> pType) {
		return (MetaDataObject<T>) classMap.get(pType);
	}

	@Override
	public <T> void add(Class<T> clazz) {
		MetaDataObject<T> meta = createMetaDataObject(clazz);
		classMap.put(clazz, meta);
		nameMap.put(clazz.getSimpleName(), meta);
	}
	
	void setConstructor(Function<Class<?>, MetaDataObject> function) {
		metaDataObjectConstructor  = function;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> MetaDataObject<T> createMetaDataObject(Class<T> clazz) {
		return metaDataObjectConstructor.apply(clazz);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <F,T> MetaDataField<F,T> createFieldMetaData(MetaDataObject<T> meta, Field field) {
		return metaDataFieldConstructor.apply(meta, field);
	}
	
}
