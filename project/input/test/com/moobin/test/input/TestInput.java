package com.moobin.test.input;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.cache.CacheMapSorting;
import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.impl.MoobinConfigurationImpl;
import com.moobin.core.Core;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestXmlInputMappings;
import com.moobin.test.example.conf.TestMoobinConfiguration;
import com.moobin.tools.InputXmlTool;

public class TestInput {

	static {
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfigurationImpl(new TestMoobinConfiguration()));
		Core.get().start();
	}
	
	public static void main(String[] args) {
		CacheMapSorting<Currency> sMap = Core.get().getCacheManager().getRootMap(Currency.class).sort((c1,c2) -> c1.code.compareTo(c2.code));
		sMap.subscribeOnIndex((i,j) -> System.out.println(sMap.getByIndex(j).code + " remove: " + i +",  add: " + j));
		load("com/moobin/test/input/Currency.xml", TestXmlInputMappings.currencyMappings());
		load("com/moobin/test/input/Country.xml", TestXmlInputMappings.countryMappings());
		System.out.println(Core.get().getCacheManager().getRootMap(Currency.class).get("SEK").name);
		Core.get().getCacheManager().getRootMap(Currency.class).filter((c) -> c.code.startsWith("S"));
		sMap.get(0, sMap.source().size() -1).stream().forEach(System.out::println);
	}
 
	private static void load(String resource, XmlInputMapping<?> mappings) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		mappings.parseAndAddToCache(doc);
	}

}
