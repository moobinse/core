package com.moobin.input.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;
import com.moobin.tools.InputXmlTool;

public class XmlInputMapping<T> {

	private Class<T> type;
	private Map<String, FieldMapping> fieldMappings = new HashMap<>();
	private MetaDataObject<T> meta;
	private String xpath;

	private class FieldMapping {

		final String xpath;
		final MetaDataField<?, T> metaField;
		
		public FieldMapping(String field, String xpath) {
			this.xpath = xpath;
			this.metaField = meta.getField(field);
		}
		
		@Override
		public String toString() {
			return "xpath mapping " + xpath + " -> " + metaField;
		}
		
	}
	
	protected XmlInputMapping() {
		// dummy object, for instantiating EntityMaps
	}
	
	public XmlInputMapping(Class<T> type, String xpath) {
		this.type = type;
		this.xpath = xpath; 
		meta = Core.get().getMetaDataManager().getMetaData(type);
	}
	
	public XmlInputMapping<T> map(String field, String xpath) {
		fieldMappings.put(field, new FieldMapping(field, xpath));
		return this;
	}

	public Class<T> getType() {
		return type;
	}


	private Collection<T> inspect(Document doc) {
		List<T> list = new ArrayList<>();
		InputXmlTool.getNodes(doc, xpath).forEach((e) -> list.add(inspect(e)));
		return list;
	}
	
	T inspect(Element element) {
		T item = meta.create();
		fieldMappings.values().forEach((fm) -> set(item, fm, InputXmlTool.getValue(element, fm.xpath)));
		return item;
	}
	
	private void set(T item, FieldMapping fm, String value) {
		if (fm.metaField != null) {
			fm.metaField.set(item, value);
		}
	}
	
	public static <T> XmlInputMapping<T> createMapping(Class<T> type, String xpath) {
		return new XmlInputMapping<T>(type, xpath);
	}
	
	public Stream<T> parseDocument(Document doc) {
		return inspect(doc).stream();
	}

}
