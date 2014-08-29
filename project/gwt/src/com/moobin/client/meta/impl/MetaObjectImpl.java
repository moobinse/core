package com.moobin.client.meta.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moobin.common.MetaFieldIf;
import com.moobin.common.MetaObjectIf;
import com.moobin.generated.client.cache.JsMetaObject;


public class MetaObjectImpl implements MetaObjectIf {

	private final JsMetaObject json;
	private Map<String, MetaFieldIf> fieldMap = new HashMap<>();
	private List<MetaFieldIf> fields = new ArrayList<>();
	
	public MetaObjectImpl(JsMetaObject json) {
		this.json = json;
		for (int i = 0; i < json.getFields().length(); i++) {
			MetaFieldIf field = new MetaFieldImpl(json.getFields().get(i));
			fieldMap.put(field.name(), field);
			fields.add(field);
		}
		fields = Collections.unmodifiableList(fields);
		fieldMap = Collections.unmodifiableMap(fieldMap);
	}
	
	@Override
	public String name() {
		return json.getName();
	}

	@Override
	public String keyField() {
		return json.getKeyField();
	}

	@Override
	public String displayField() {
		return json.getDisplayField();
	}

	@Override
	public MetaFieldIf getField(String name) {
		return fieldMap.get(name);
	}
	
	@Override
	public List<MetaFieldIf> getFields() {
		return fields;
	}
	
	
	
}
