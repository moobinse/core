package com.moobin.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.core.Core;
import com.moobin.core.MoobinException;

public class MetaDataObjectImpl<T> implements MetaDataObject<T> {

	private Class<T> type;
	private String name;
	private MetaDataField<String, T> displayField;
	private MetaDataField<String, T> keyField;
	private boolean cacheRoot;
	private List<MetaDataField<?, T>> allFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> simpleFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> objectFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> simpleArrayFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> objectArrayFields = new ArrayList<MetaDataField<?,T>>();
	private Map<String, MetaDataField<?, T>> fieldMap = new HashMap<String, MetaDataField<?,T>>();
	private Constructor<T> constructor;
	
	public MetaDataObjectImpl(Class<T> clazz) {
		type = clazz;
		name = clazz.getSimpleName();
		Arrays.asList(clazz.getFields()).forEach(this::add);
		try {
			this.constructor = clazz.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new MoobinException(e);
		}
	}
	
	@Override
	public Class<T> getType() {
		return type;
	}
	
	@SuppressWarnings("unchecked")
	private <F> void add(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) return;
		if (Modifier.isStatic(field.getModifiers())) return;
		MetaDataField<F, T> meta = Core.get().getMetaDataManager().createFieldMetaData(this, field);
		fieldMap.put(meta.getName(), meta);
		allFields.add(meta);
		switch (meta.getType()) {
			case SIMPLE: simpleFields.add(meta); break;
			case OBJECT: objectFields.add(meta); break;
			case SIMPLE_ARRAY: simpleArrayFields.add(meta); break;
			case OBJECT_ARRAY: objectArrayFields.add(meta); break;
		}
		if (field.getAnnotation(Id.class) != null) {
			assert keyField == null;
			keyField = (MetaDataField<String, T>) meta;
		}
		if (field.getAnnotation(BtDisplay.class) != null) {
			assert displayField == null;
			displayField = (MetaDataField<String, T>) meta;
		}
	}

	@Override
	public T create() {
		try {
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new MoobinException(e);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <F> MetaDataField<F, T> getField(String field) {
		return (MetaDataField<F, T>) fieldMap.get(field);
	}
	
	@Override
	public List<MetaDataField<?, T>> getFields() {
		return allFields;
	}
	
	@Override
	public MetaDataField<String, T> getKeyField() {
		return keyField;
	}
	
	@Override
	public MetaDataField<String, T> getDisplayField() {
		return displayField;
	}
	
	@Override
	public boolean isCacheRoot() {
		return cacheRoot;
	}

	@Override
	public List<MetaDataField<?, T>> getSimpleFields() {
		return simpleFields;
	}

	@Override
	public List<MetaDataField<?, T>> getSimpleArrayFields() {
		return simpleArrayFields;
	}

	@Override
	public List<MetaDataField<?, T>> getObjectFields() {
		return objectFields;
	}

	@Override
	public List<MetaDataField<?, T>> getObjectArrayFields() {
		return objectArrayFields;
	}

	@Override
	public void setCacheRoot(boolean b) {
		cacheRoot = b;
	}
	
}
