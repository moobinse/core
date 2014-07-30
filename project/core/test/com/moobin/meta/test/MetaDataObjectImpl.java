package com.moobin.meta.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;

import com.moobin.core.MoobinException;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

public class MetaDataObjectImpl<T> implements MetaDataObject<T> {

	private Function<T, String> keyFunction;
	private Function<T, String> textFunction;
	private Class<T> clazz;

	public MetaDataObjectImpl(Class<T> clazz, Function<T, String> key, Function<T, String> text) {
		this.clazz = clazz;
		this.keyFunction = key;
		this.textFunction = text;
	}

	@Override
	public T create() {
		// TODO Auto-generated method stub
		return null;
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
	public MetaDataField<?, T> getField(String field) {
		return null;
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
