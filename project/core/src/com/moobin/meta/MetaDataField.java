package com.moobin.meta;

import java.util.function.Function;

public interface MetaDataField<F,T> {
	
	enum Type { SIMPLE, SIMPLE_ARRAY, OBJECT, OBJECT_ARRAY }

	String getName();
	
	Class<F> getJavaType();

	Type getType();
	
	boolean isArray();
	
	boolean isRequired();
	
	boolean isUnique();
	
	default Function<T, F> getFunction() {
		return (t) -> get(t);
	}
	
	F get(T item);
	
	void set(T item, String value);

}
