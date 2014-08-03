package com.moobin.test.example.conf;

import com.moobin.configuration.impl.AbstractMoobinConfigurationSource;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestItem;
import com.moobin.test.example.TestItem_dummy;

public class TestMoobinConfiguration extends AbstractMoobinConfigurationSource {

	public TestMoobinConfiguration() {
		
		// meta data
		
		addMetaEntity(
			TestItem.class,
			Country.class,
			Currency.class);
		
		addMetaDataField(TestItem.class, "dummy", new TestItem_dummy());
		
		removeMetaDataField(TestItem.class, "name");

		// cache
		
		addCacheRoot(
			TestItem.class,
			Country.class,
			Currency.class);
		
	}

}
