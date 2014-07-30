package com.moobin.test.input;

import java.net.URL;
import java.util.stream.Stream;

import org.w3c.dom.Document;

import com.moobin.cache.MoobinCacheManager;
import com.moobin.cache.impl.CacheMap;
import com.moobin.core.Core;
import com.moobin.input.xml.InputXmlTool;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.meta.MetaDataManager;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestXmlInputMappings;

public class TestInput {

	static CacheMap<Currency> cCcy = new CacheMap<Currency>((c) -> true, (c) -> c.code);
	
	static {
		Core.set(new MetaDataManagerImpl());
		Core.set(new MoobinCacheManager() {
			

			@Override
			public void add(Object item) {
				if (item instanceof Currency) {
					cCcy.add((Currency) item);
					System.out.println(cCcy.size());
				}
			}
			
			@Override
			public void addStream(Stream<? extends Object> stream) {
				stream.forEach(this::add);
			}
			
		});
	}
	
	public static void main(String[] args) {
		MetaDataManager metaManager = Core.get().getMetaDataManager();
		metaManager.add(Country.class);
		metaManager.add(Currency.class);
		TestXmlInputMappings.init();
		load("com/moobin/test/input/Currency.xml", Currency.class);
		load("com/moobin/test/input/Country.xml", Country.class);
		System.out.println(cCcy.get("SEK").name);
	}
 
	private static void load(String resource, Class<?>... classes) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		XmlInputMapping.parseDocument(doc).forEach(System.out::println);
		XmlInputMapping.parseDocument(doc).forEach(Core.get().getCacheManager()::add);
	}

}
