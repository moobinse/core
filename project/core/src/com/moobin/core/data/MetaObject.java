package com.moobin.core.data;

import com.moobin.annotation.Id;
import com.moobin.meta.MetaDataObject;

public class MetaObject {
	

	@Id
	public String name;
	
	public MetaField[] fields;
	
	public static MetaObject create(MetaDataObject<?> source) {
		MetaObject m = new MetaObject();
		m.name = source.getName();
		m.fields = new MetaField[source.getFields().size()];
		for (int i = 0; i < m.fields.length; i++) {
			m.fields[i] = MetaField.create(source.getFields().get(i));
		}
		return m;
	}
}
