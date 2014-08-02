package com.moobin.test.input;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.cache.CacheMap;
import com.moobin.cache.CacheMapSorting;
import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.MoobinConfigurationSource;
import com.moobin.core.Core;
import com.moobin.input.xml.InputXmlTool;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestMoobinConfiguration;
import com.moobin.test.example.TestXmlInputMappings;

public class TestInput {

	static {
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfiguration() {
			MoobinConfigurationSource source = new TestMoobinConfiguration();
			@Override
			public MoobinConfigurationSource source() {
				return source;
			}
		});
		Core.get().start();
	}
	
	public static void main(String[] args) {
		TestXmlInputMappings.init();
		CacheMapSorting<Currency> sMap = Core.get().getCacheManager().getRootMap(Currency.class).sort((c1,c2) -> c1.code.compareTo(c2.code));
		sMap.subscribeOnIndex((i,j) -> System.out.println(sMap.getByIndex(j).code + " remove: " + i +",  add: " + j));
		load("com/moobin/test/input/Currency.xml", Currency.class);
		load("com/moobin/test/input/Country.xml", Country.class);
		System.out.println(Core.get().getCacheManager().getRootMap(Currency.class).get("SEK").name);
		CacheMap<Currency> fMap = Core.get().getCacheManager().getRootMap(Currency.class).filter((c) -> c.code.startsWith("S"));
		sMap.get(0, sMap.source().size() -1).stream().forEach(System.out::println);
	}
 
	private static void load(String resource, Class<?>... classes) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		XmlInputMapping.parseDocument(doc).forEach(Core.get().getCacheManager()::add);
	}

}
