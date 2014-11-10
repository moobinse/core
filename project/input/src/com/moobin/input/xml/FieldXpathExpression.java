package com.moobin.input.xml;

import org.w3c.dom.Element;

import com.moobin.meta.MetaDataField;
import com.moobin.tools.InputXmlTool;

class FieldXpathExpression<T> extends AbstractFieldMapping<T> {

	final String xpath;
	public FieldXpathExpression(MetaDataField<?, T> field, String xpath) {
		super(field);
		this.xpath = xpath;
	}
	
	@Override
	void apply(Element element, T item) {
		set(item, InputXmlTool.getValue(element, xpath));
	}
	
}