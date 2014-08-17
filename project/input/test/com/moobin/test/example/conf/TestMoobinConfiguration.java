package com.moobin.test.example.conf;

import com.moobin.cache.CacheRootMap;
import com.moobin.configuration.impl.AbstractMoobinConfiguration;
import com.moobin.core.Core;
import com.moobin.test.example.Country;
import com.moobin.test.example.Currency;
import com.moobin.test.example.TestItem;

public class TestMoobinConfiguration extends AbstractMoobinConfiguration {

	public TestMoobinConfiguration() {
		
		// meta data
		
		addMetaEntity(
				TestItem.class,
				Country.class,
				Currency.class);
		// cache
		
		addCacheRoot(
				TestItem.class,
				Country.class,
				Currency.class);

		// add-remove meta data fields
		
		CacheRootMap<Currency> ccyRoot = Core.get().getCacheManager().getRootMap(Currency.class);
		addMetaDataField(TestItem.class, "dummy", String.class, (t) -> "dummy");
		addMetaDataField(TestItem.class, "ccyCode", String.class, (t) -> ccyRoot.get(t.id).code);
		removeMetaDataField(TestItem.class, "ccy");
		
	}
	
	
}
