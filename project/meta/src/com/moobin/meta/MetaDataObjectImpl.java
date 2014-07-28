package com.moobin.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.Id;
import com.moobin.core.Core;

public class MetaDataObjectImpl<T> implements MetaDataObject<T> {

	private String name;
	private MetaDataField<String, T> displayField;
	private MetaDataField<?, T> keyField;
	private List<MetaDataField<?, T>> allFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> simpleFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> objectFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> simpleArrayFields = new ArrayList<MetaDataField<?,T>>();
	private List<MetaDataField<?, T>> objectArrayFields = new ArrayList<MetaDataField<?,T>>();
	private Map<String, MetaDataField<?, T>> fieldMap = new HashMap<String, MetaDataField<?,T>>();
	
	public MetaDataObjectImpl(Class<T> clazz) {
		name = clazz.getSimpleName();
		Arrays.asList(clazz.getFields()).forEach(this::add);
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
			keyField = meta;
		}
		if (field.getAnnotation(BtDisplay.class) != null) {
			assert displayField == null;
			displayField = (MetaDataField<String, T>) meta;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<MetaDataField<?, T>> getFields() {
		return allFields;
	}
	
	@Override
	public MetaDataField<?, T> getKeyField() {
		return keyField;
	}
	
	@Override
	public MetaDataField<String, T> getDisplayField() {
		return displayField;
	}

	@Override
	public Function<T, ?> keyFunction() {
		return getKeyField().getFunction();
	}

	@Override
	public Function<T, String> displayFunction() {
		return getDisplayField().getFunction();
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

	
}
