package com.moobin.test.input;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.cache.impl.CacheMapImpl;
import com.moobin.core.Core;
import com.moobin.input.xml.InputXmlTool;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.meta.MetaDataManager;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestXmlInputMappings;

public class TestInput {

	static {
		Core.set(new MetaDataManagerImpl());
		Core.get().getMetaDataManager().add(Currency.class);
		Core.get().getMetaDataManager().add(Country.class);

		Core.set(new CacheManagerImpl());
		Core.get().getCacheManager().createMapping(Currency.class);
		Core.get().getCacheManager().createMapping(Country.class);
	}
	
	public static void main(String[] args) {
		TestXmlInputMappings.init();
		load("com/moobin/test/input/Currency.xml", Currency.class);
		load("com/moobin/test/input/Country.xml", Country.class);
		System.out.println(Core.get().getCacheManager().get(Currency.class).get("SEK").name);
	}
 
	private static void load(String resource, Class<?>... classes) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		XmlInputMapping.parseDocument(doc).forEach(Core.get().getCacheManager()::add);
	}

}
