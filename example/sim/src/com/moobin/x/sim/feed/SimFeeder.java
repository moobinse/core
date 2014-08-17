package com.moobin.x.sim.feed;

import java.net.URL;

import org.w3c.dom.Document;

import com.moobin.core.Core;
import com.moobin.feed.MoobinFeeder;
import com.moobin.input.xml.XmlInputMapping;
import com.moobin.tools.InputXmlTool;

public class SimFeeder implements MoobinFeeder, Runnable {

	private Thread thread;

	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void stop() {
	}
 
	private static <T> void load(String resource, XmlInputMapping<T> mapping) {
		URL url = InputXmlTool.getResource(resource);
		Document doc = InputXmlTool.getDocument(url);
		mapping.parseDocument(doc).forEach(Core.get().getCacheManager()::add);
	}

	@Override
	public void run() {
		load("resources/Currency.xml", SimXmlMappings.currencyXmlMapping);
		load("resources/Country.xml", SimXmlMappings.countryXmlMapping);
	}
	
}
