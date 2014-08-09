package com.moobin.x.sim.configuration;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.core.Core;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.tools.InputXmlTool;
import com.moobin.x.sim.Country;

public class Sim {

	public static void main(String[] args) {
		SimCore.init();
		load("resources/Currency.xml", SimXmlMappings.currencyXmlMapping);
		load("resources/Country.xml", SimXmlMappings.countryXmlMapping);
		Core.get().getCacheManager().getRootMap(Country.class).filter((c) -> c.name.startsWith("S")).stream().
			map((c) -> c.name).forEach(System.out::println);
	}
 
	private static <T> void load(String resource, XmlInputMapping<T> mapping) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		mapping.parseDocument(doc).forEach(Core.get().getCacheManager()::add);
	}
}
