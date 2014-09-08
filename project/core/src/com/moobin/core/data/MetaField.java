package com.moobin.core.data;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;

@Action(MetaAction.GET)
public class MetaField {

	@Id
	public String key;
	
	public String parent;
	
	@BtDisplay
	public String name;
	
	public String businessType;
	
	public String javaType;

	public MetaField() {
		// dummy constructor
	}
	
	private MetaField(MetaDataField<?, ?> metaDataField) {
		name = metaDataField.getName();
		businessType = metaDataField.getType().toString();
		javaType = metaDataField.getJavaType().getSimpleName();
		parent = metaDataField.getParent().getName();
		key = parent + "." + name;
		Core.get().getCacheManager().add(this);
	}

	public static MetaField create(MetaDataField<?, ?> metaDataField) {
		return new MetaField(metaDataField);
	}
	
}
