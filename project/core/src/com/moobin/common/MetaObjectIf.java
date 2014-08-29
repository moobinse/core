package com.moobin.common;

import java.util.List;

public interface MetaObjectIf {
	
	String name();
	
	String keyField();
	
	String displayField();
	
	MetaFieldIf getField(String name);

	List<MetaFieldIf> getFields();
 
}
