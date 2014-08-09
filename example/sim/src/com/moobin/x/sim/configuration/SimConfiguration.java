package com.moobin.x.sim.configuration;

import com.moobin.configuration.MoobinConfigurationSource;
import com.moobin.configuration.impl.AbstractMoobinConfigurationSource;
import com.moobin.test.example.Currency;
import com.moobin.x.sim.Account;
import com.moobin.x.sim.Country;
import com.moobin.x.sim.Market;
import com.moobin.x.sim.MarketList;
import com.moobin.x.sim.Member;
import com.moobin.x.sim.User;

public class SimConfiguration extends AbstractMoobinConfigurationSource {

	private static final MoobinConfigurationSource config = new SimConfiguration();

	private SimConfiguration() {
		
		// meta data
		
		addMetaEntity(
				Account.class,
				Country.class,
				Currency.class,
				Market.class,
				MarketList.class,
				Member.class,
				User.class);
		
		// cache
		
		addCacheRoot(
				Account.class,
				Country.class,
				Currency.class,
				Market.class,
				MarketList.class,
				Member.class,
				User.class);
		
		// add-remove meta data fields
		
//		CacheRootMap<Currency> ccyRoot = Core.get().getCacheManager().getRootMap(Currency.class);
//		addMetaDataField(TestItem.class, "dummy", (t) -> "dummy");
//		addMetaDataField(TestItem.class, "ccyCode", (t) -> ccyRoot.get(t.id).code);
//		removeMetaDataField(TestItem.class, "ccy");
		
		
	}

	public static MoobinConfigurationSource get() {
		return config;
	}
	
}
