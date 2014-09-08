package com.moobin.x.sim.configuration;

import com.moobin.configuration.CoreConfig;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.impl.AbstractMoobinConfiguration;
import com.moobin.tools.MoobinConfigurationTool;
import com.moobin.x.sim.Account;
import com.moobin.x.sim.Country;
import com.moobin.x.sim.Currency;
import com.moobin.x.sim.Market;
import com.moobin.x.sim.MarketList;
import com.moobin.x.sim.Member;
import com.moobin.x.sim.User;
import com.moobin.x.sim.feed.SimFeeder;

public class SimConfiguration extends AbstractMoobinConfiguration {

	private SimConfiguration() {
		
		inherits(CoreConfig.class); 
		feeder(SimFeeder.class);
		
		// meta data
		addMetaEntity(
				Account.class,
				Country.class,
				Currency.class,
				Market.class,
				MarketList.class,
				Member.class,
				User.class);

		// add-remove meta data fields
		//addMetaDataField(Country.class, "test", String.class, SimFunctions.country_test);
		removeMetaDataField(Country.class, "test");
		
		// cache
		
		addCacheRoot(
				Account.class,
				Country.class,
				Currency.class,
				Market.class,
				MarketList.class,
				Member.class,
				User.class);
		
		
//		CacheRootMap<Currency> ccyRoot = Core.get().getCacheManager().getRootMap(Currency.class);
//		addMetaDataField(TestItem.class, "dummy", (t) -> "dummy");
//		addMetaDataField(TestItem.class, "ccyCode", (t) -> ccyRoot.get(t.id).code);
//		removeMetaDataField(TestItem.class, "ccy");
		
	}
	
	public static void main(String[] args) {
		MoobinConfigurationTool.toXml(get(), System.out);
	}

	public static MoobinConfiguration get() {
		return MoobinConfiguration.getConfig(SimConfiguration.class);
	}
	
}
