package com.moobin.output.js;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;


public class MoobinFromXml<T> {

	private T item;
	private MetaDataObject<T> meta;
	private Element json;
	
	private MoobinFromXml(Class<T> javaType, Element value) {
		json = value;
		meta = Core.get().getMetaDataManager().getMetaData(javaType);
		item = meta.create();
		getChildren(json).forEach(this::addElement);
		for (int i = 0; i < value.getAttributes().getLength(); i++) {
			addAttribute(value.getAttributes().item(i));
		}
	}
	
	private static List<Element> getChildren(Element e) {
		NodeList nodes = e.getChildNodes();
		List<Element> list = new ArrayList<>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == node.ELEMENT_NODE) {
				list.add((Element) node);
			}
		}
		return list;
	}

	private void addElement(Element e) {
		MetaDataField<Object, T> field = meta.getField(e.getTagName());
		if (field != null) {
			if (field.getType() == Type.OBJECT) {
				field.set(item, parse(field.getJavaType(), e));
			}
			else {
				String value = e.getFirstChild().getNodeValue();
				field.set(item, value);
			}
		}
	}
	
	private void addAttribute(Node attr) {
		MetaDataField<Object, T> field = meta.getField(attr.getNodeName());
		if (field != null) {
			field.set(item, attr.getNodeValue());
		}
 	}
	
	public static <T> T parse(Class<T> clazz, Element json) {
		return new MoobinFromXml<T>(clazz, json).item;
	}
	
	public static <T> T parse(Class<T> clazz, String in) {
		InputSource source = new InputSource(new StringReader(in));
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList x = (NodeList) xPath.evaluate("/", source, XPathConstants.NODESET);
			Document e = (Document) x.item(0);
			return new MoobinFromXml<T>(clazz, e.getDocumentElement()).item;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
