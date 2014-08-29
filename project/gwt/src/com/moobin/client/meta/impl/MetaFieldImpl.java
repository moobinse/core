package com.moobin.client.meta.impl;

import com.moobin.common.MetaFieldIf;
import com.moobin.generated.client.cache.JsMetaField;

public class MetaFieldImpl implements MetaFieldIf {

	
	private JsMetaField json;

	public MetaFieldImpl(JsMetaField json) {
		this.json = json;
	}
	
	@Override
	public String name() {
		return json.getName();
	}
	
	@Override
	public String getBusinessType() {
		return json.getBusinessType();
	}
	
	@Override
	public String getJavaType() {
		return json.getJavaType();
	}

}
