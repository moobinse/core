package com.moobin.meta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.moobin.annotation.Required;
import com.moobin.annotation.Unique;
import com.moobin.core.MoobinException;

public class MetaDataFieldImpl<F,T> implements MetaDataField<F, T> {

	private String objectName;
	private String fieldName;
	private Type type;
	private boolean unique;
	private boolean array;
	private boolean required;
	
	private Class<?> javaType;
	private Field field;
	
	static List<Class<?>> simpleTypes = Arrays.asList(
			String.class, 
			boolean.class, Boolean.class,
			int.class, Integer.class,
			long.class, Long.class);
	
	MetaDataFieldImpl(MetaDataObject<T> parent, Field field) {
		this.field = field;
		objectName = parent.getName();
		fieldName = field.getName();
		Class<?> datatype = field.getType();
		array = datatype.isArray();
		javaType = array ? datatype.getComponentType() : datatype;
		unique = field.getAnnotation(Unique.class) != null;
		required = field.getAnnotation(Required.class) != null;
		if (simpleTypes.contains(javaType)) {
			type = array ? Type.SIMPLE_ARRAY : Type.SIMPLE;
		}
		else {
			type = array ? Type.OBJECT_ARRAY : Type.OBJECT;
		}
	}
	
	@Override
	public String getName() {
		return fieldName;
	}
	
	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean isRequired() {
		return required;
	}
	
	@Override
	public boolean isArray() {
		return array;
	}
	
	@Override
	public boolean isUnique() {
		return unique;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<F> getJavaType() {
		return (Class<F>) javaType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public F get(T item) {
		try {
			return (F) field.get(item);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new MoobinException();
		}
	}

	@Override
	public Function<T, F> getFunction() {
		return (t) -> get(t);
	}
	
	@Override
	public String toString() {
		return objectName + "." + fieldName + "[" + javaType + "]";
	}

}
