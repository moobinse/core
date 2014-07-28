package com.moobin.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import com.moobin.annotation.BtDecimal;
import com.moobin.annotation.BtNumber;
import com.moobin.annotation.BtText;

@SuppressWarnings("rawtypes")
public class MetaDataManagerImpl implements MetaDataManager {

	private Map<Class<?>, MetaDataObject<?>> classMap = new HashMap<Class<?>, MetaDataObject<?>>();
	private Map<String, MetaDataObject<?>> nameMap = new HashMap<String, MetaDataObject<?>>();
	private Function<Class<?>, MetaDataObject> metaDataObjectConstructor = MetaDataObjectImpl::new;
	private BiFunction<MetaDataObject, Field, MetaDataField> metaDataFieldConstructor = MetaDataFieldImpl::new;
	private List<Class<? extends Annotation>> businessTypeAnnotations = 
			Arrays.asList(BtNumber.class, BtText.class, BtDecimal.class);
	
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
	
	@Override
	public boolean isBusinessTypeAnnotation(Annotation annotation) {
		return businessTypeAnnotations.contains(annotation.annotationType());
	}
	
}
