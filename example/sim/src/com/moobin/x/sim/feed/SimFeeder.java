package com.moobin.x.sim.feed;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.feed.MoobinFeeder;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.tools.InputXmlTool;

public class SimFeeder implements MoobinFeeder {

	public void start() {
		load("resources/Currency.xml", SimXmlMappings.currencyXmlMapping);
		load("resources/Country.xml", SimXmlMappings.countryXmlMapping);
	}
	
	@Override
	public void stop() {
	}
 
	private static <T> void load(String resource, XmlInputMapping<T> mapping) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		mapping.parseAndAddToCache(doc);
	}

}
