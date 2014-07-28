package com.moobin.meta.test;

import java.util.List;
import java.util.function.Function;

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
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaDataField<?, T> getKeyField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaDataField<String, T> getDisplayField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaDataField<?, T>> getSimpleFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaDataField<?, T>> getSimpleArrayFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaDataField<?, T>> getObjectFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaDataField<?, T>> getObjectArrayFields() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
