package com.moobin.input.xml;

import org.w3c.dom.Element;

import com.moobin.meta.MetaDataField;

class FieldAttribute<T> extends AbstractFieldMapping<T> {

	final String xmlAttribute;
	public FieldAttribute(MetaDataField<?, T> field, String xmlAttribute) {
		super(field);
		this.xmlAttribute = xmlAttribute;
	}
	
	@Override
	void apply(Element element, T item) {
		set(item, element.getAttribute(xmlAttribute));
	}
}