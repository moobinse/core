package com.moobin.generate.tools;

import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.impl.AbstractMoobinConfiguration;
import com.moobin.output.test.InOutTest;
import com.moobin.output.test.data.Address;
import com.moobin.output.test.data.Member;
import com.moobin.output.test.data.User;
import com.moobin.test.meta.Country;
import com.moobin.test.meta.Currency;

public class ExampleConfiguration extends AbstractMoobinConfiguration {

	private static final MoobinConfiguration config = new ExampleConfiguration();
	
	public static MoobinConfiguration get() {
		return config;
	}
	
	private ExampleConfiguration()
	{
		addCacheRoot(InOutTest.class); 
		addCacheRoot(Country.class);
		addCacheRoot(Currency.class);
		addCacheRoot(User.class);
		addCacheRoot(Member.class);
		addCacheRoot(Address.class);
	}

}
