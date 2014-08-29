package com.moobin.core.data;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.meta.MetaDataObject;

public class MetaObject {
	

	@Id
	@BtDisplay
	public String name;

	public String keyField;
	
	public String displayField;
	
	public boolean cacheRoot;
	
	public MetaField[] fields;
	
	public static MetaObject create(MetaDataObject<?> source) {
		MetaObject m = new MetaObject();
		m.name = source.getName();
		m.displayField = source.getDisplayField() != null ? source.getDisplayField().getName() : null;
		m.keyField = source.getKeyField() != null ? source.getKeyField().getName() : null;
		m.cacheRoot = source.isCacheRoot();
		m.fields = new MetaField[source.getFields().size()];
		for (int i = 0; i < m.fields.length; i++) {
			m.fields[i] = MetaField.create(source.getFields().get(i));
		}
		return m;
	}
}
