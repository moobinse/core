package com.moobin.input.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.moobin.cache.CacheManager;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataObject;
import com.moobin.tools.InputXmlTool;

public class XmlInputMapping<T> {

	private Class<T> type;
	private List<AbstractFieldMapping<T>> mappings = new ArrayList<>();
	private MetaDataObject<T> meta;
	private String xpath;
	
	protected XmlInputMapping() {
		// dummy object, for instantiating EntityMaps
	}
	
	public XmlInputMapping(Class<T> type, String xpath) {
		this.type = type;
		this.xpath = xpath; 
		meta = Core.get().getMetaDataManager().getMetaData(type);
	}
	
	public void mapXpath(String field, String xpath) {
		mappings.add(new FieldXpathExpression<T>(meta.getField(field), xpath));
	}

	public void mapAttribute(String field) {
		mapAttribute(field, field);
	}
	
	public void mapAttribute(String field, String xmlAttribute) {
		mappings.add(new FieldAttribute<T>(meta.getField(field), xmlAttribute));
	}

	public void mapTextElement(String field, String xmlElementName) {
		mappings.add(new FieldXmlElement<T>(meta.getField(field), xmlElementName));
	}
	
	public void mapFunction(String field, Function<T, String> function) {
		mappings.add(new FieldFunction<T>(meta.getField(field), function));
	}
	
	public void mapFunction(String field, BiFunction<T, Element, String> function) {
		mappings.add(new FieldBiFunction<T>(meta.getField(field), function));
	}
	
	public void mapTrim(Consumer<T> consumer) {
		mappings.add(new FieldTrimming<T>(consumer));
	}

	public Class<T> getType() {
		return type;
	}


	private Collection<T> inspect(Document doc) {
		List<T> list = new ArrayList<>();
		InputXmlTool.getElementStream(doc, xpath).forEach((e) -> list.add(inspect(e)));
		return list;
	}
	
	T inspect(Element element) {
		T item = meta.create();
		mappings.forEach((m) -> m.apply(element, item));
		return item;
	}
	
	public static <T> XmlInputMapping<T> createMapping(Class<T> type, String xpath) {
		return new XmlInputMapping<T>(type, xpath);
	}
	
	public Stream<T> parseDocument(Document doc) {
		return inspect(doc).stream();
	}
	
	public void parseAndAddToCache(Document doc) {
		CacheManager cache = Core.get().getCacheManager();
		parseDocument(doc).forEach(cache::add);
	}

}
