package com.moobin.input.xml;

import org.w3c.dom.Element;

import com.moobin.meta.MetaDataField;

abstract class AbstractFieldMapping<T> {

	/**
	 * 
	 */
	final MetaDataField<?, T> metaDataField;

	public AbstractFieldMapping(MetaDataField<?, T> metaDataField) {
		this.metaDataField = metaDataField;
	}
	
	abstract void apply(Element element, T item);
	
	protected void set(T item, String value) {
		metaDataField.set(item, value);
	}
	
}