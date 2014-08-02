package com.moobin.test.example;

import com.moobin.configuration.impl.AbscractMoobinConfigurationSource;

public class TestMoobinConfiguration extends AbscractMoobinConfigurationSource {

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
