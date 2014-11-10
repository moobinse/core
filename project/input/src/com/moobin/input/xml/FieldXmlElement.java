package com.moobin.input.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.moobin.meta.MetaDataField;

class FieldXmlElement<T> extends AbstractFieldMapping<T> {

	final String xmlElementName;

	public FieldXmlElement(MetaDataField<?, T> field, String xmlElementName) {
		super(field);
		this.xmlElementName = xmlElementName;
	}

	@Override
	void apply(Element element, T item) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child instanceof Element && ((Element) child).getTagName().equals(xmlElementName)) {
				if (child.getFirstChild() != null) {
					set(item, child.getFirstChild().getNodeValue());
				}
				return;
			}
		}
	}
}