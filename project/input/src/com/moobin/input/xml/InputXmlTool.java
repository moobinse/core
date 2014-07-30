package com.moobin.input.xml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.moobin.core.MoobinException;
import com.sun.org.apache.xpath.internal.XPathAPI;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class InputXmlTool {
	
	public static URL getResource(String resource){

	    URL url ;

	    //Try with the Thread Context Loader. 
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    if(classLoader != null){
	        url = classLoader.getResource(resource);
	        if(url != null){
	            return url;
	        }
	    }

	    //Let's now try with the classloader that loaded this class.
	    classLoader = Loader.class.getClassLoader();
	    if(classLoader != null){
	        url = classLoader.getResource(resource);
	        if(url != null){
	            return url;
	        }
	    }

	    //Last ditch attempt. Get the resource from the classpath.
	    return ClassLoader.getSystemResource(resource);
	}
	
	public static Document getDocument(URL url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(url.openStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new MoobinException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<Element> getNodes(Document doc, String xpath) {
		NodeList nodes;
		try {
			nodes = XPathAPI.selectNodeList(doc.getDocumentElement(), xpath);
			if (nodes.getLength() == 0) {
				return Collections.EMPTY_LIST;
			}
			List<Element> elements = new ArrayList();
			for (int i = 0; i < nodes.getLength(); i++) {
				elements.add((Element) nodes.item(i));
			}
			return elements;
		} catch (TransformerException e) {
			throw new MoobinException(e);
		}
	}

	public static String getValue(Element element, String xpath) {
		try {
			Node node = XPathAPI.selectSingleNode(element, xpath);
			if (node == null) {
				return null;
			}
			String nodeValue = node.getNodeValue();
			if (nodeValue == null) {
				nodeValue = node.getFirstChild().getNodeValue();
			}
			return nodeValue;
		} catch (TransformerException e) {
			throw new MoobinException(e);
		}
	}
}
