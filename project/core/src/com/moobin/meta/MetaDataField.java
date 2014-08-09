package com.moobin.meta;


public interface MetaDataField<F,T> {
	
	enum Type { SIMPLE, SIMPLE_ARRAY, OBJECT, OBJECT_ARRAY }

	String getName();
	
	Class<F> getJavaType();

	Type getType();
	
	Class<?> getReferenceType();

	boolean isArray();
	
	boolean isRequired();
	
	boolean isUnique();
	
	F get(T item);

	void set(T item, Object value);


}
