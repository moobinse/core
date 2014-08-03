package com.moobin.output.test;

import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.MoobinConfigurationSource;
import com.moobin.configuration.impl.AbstractMoobinConfigurationSource;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.output.js.MoobinFormatterJson;
import com.moobin.output.js.MoobinFormatterXml;
import com.moobin.output.js.MoobinFormatterXmlCompact;
import com.moobin.output.js.MoobinTo2;
import com.moobin.output.js.MoobinToJson;
import com.moobin.output.js.MoobinToXml;
import com.moobin.output.js.MoobinToXmlCompact;

public class TestOutput {

	static {
		MoobinConfigurationSource config = new AbstractMoobinConfigurationSource() {
			{
				addMetaEntity(InOutTest.class);
			}
		};
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new MoobinConfiguration() {
			@Override
			public MoobinConfigurationSource source() {
				return config;
			}
		});
		Core.get().start();
	}
	
	public static void main(String[] args) {
		InOutTest t = new InOutTest();
		t.id = "U137";
		t.name = "Moobin";
		t.boo = true;
		t.child = new InOutTest();
		t.child.id = "U137.99";
		System.out.println(new MoobinToJson(t));
		System.out.println(new MoobinToXml(t));
		System.out.println(new MoobinToXmlCompact(t));
		System.out.println(new MoobinTo2(t, 0, new StringBuffer()).toString());
		System.out.println(new MoobinFormatterXml(t, 0, new StringBuffer(),null).toString());
		System.out.println(new MoobinFormatterXmlCompact(t, 0, new StringBuffer(),null).toString());
		System.out.println(new MoobinFormatterJson(t, 0, new StringBuffer()).toString());
	}

}
