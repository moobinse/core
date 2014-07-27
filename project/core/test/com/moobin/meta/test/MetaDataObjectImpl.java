package com.moobin.meta.test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

public class MetaDataObjectImpl<T> implements MetaDataObject<T> {

	Function<T, String> keyFunction;
	Function<T, String> textFunction;
	Class<T> clazz;

	public MetaDataObjectImpl(Class<T> clazz, Function<T, String> key, Function<T, String> text) {
		this.clazz = clazz;
		this.keyFunction = key;
		this.textFunction = text;
	}
	
	@Override
	public Function<T, String> keyFunction() {
		return keyFunction;
	}

	@Override
	public Function<T, String> displayFunction() {
		return textFunction;
	}

	@Override
	public List<MetaDataField<?, T>> getFields() {
		return null;
	}

	@Override
	public List<MetaDataField<?, T>> getFields(
			Predicate<MetaDataField<?, ?>> filter) {
		return null;
	}
	

	
}
