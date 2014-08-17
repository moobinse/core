package com.moobin.output.test;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.impl.AbstractMoobinConfiguration;
import com.moobin.configuration.impl.MoobinConfigurationImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.output.js.MoobinFromJson;
import com.moobin.output.js.MoobinFromXml;
import com.moobin.output.js.MoobinToJson;
import com.moobin.tools.MoobinConfigurationTool;

public class TestOutput {

	static {
		MoobinConfiguration config = new AbstractMoobinConfiguration() {
			{
				addMetaEntity(InOutTest.class);
			}
		};
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MoobinConfigurationImpl(config));
		Core.get().start();
		MoobinConfigurationTool.toXml(config, System.out);
	}
	
	public static void main(String[] args) {
		InOutTest t0 = InOutTest.create();
		String json0 = MoobinToJson.format(t0);
		InOutTest t1 = MoobinFromJson.parse(InOutTest.class, json0);
		String json1 = MoobinToJson.format(t1);
		System.out.println(json0.equals(json1));
		System.out.println(json0);
		System.out.println(json1);
		
		InOutTest t3 = MoobinFromXml.parse(InOutTest.class, 
				"<InOutTest>" +
				"  <id>Id 1</id>" +
			    "  <boo>true</boo>" +
			    "  <ints>1,4,8</ints>" +
				"  <ccy>SEK2</ccy>" +
				"</InOutTest>");
		System.out.println(MoobinToJson.format(t3));
		InOutTest t4 = MoobinFromXml.parse(InOutTest.class, 
				"<InOutTest id='Id 2'>" +
			    "  <boo>true</boo>" +
			    "  <ints>1,4,8</ints>" +
				"  <ccy>SEK2</ccy>" +
			    "  <child><id>Flinta88</id></child>" +
				"</InOutTest>");
		System.out.println(MoobinToJson.format(t4));
	}

}
