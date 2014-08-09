package com.moobin.x.sim.configuration;

import com.moobin.configuration.impl.AbstractMoobinConfigurationSource;
import com.moobin.x.sim.Account;
import com.moobin.x.sim.Organisation;
import com.moobin.x.sim.User;

public class XSimConfiguration extends AbstractMoobinConfigurationSource {

	public XSimConfiguration() {
		
		// meta data
		
		addMetaEntity(
//			Account.class,
//			Organisation.class,
//			User.class
			);
		
		// cache
		
		addCacheRoot(
				Account.class,
				Organisation.class,
				User.class);
		
		
		
	}
	
}
