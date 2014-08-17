package com.moobin.configuration.impl;

import com.moobin.configuration.ConfigurationManager;
import com.moobin.configuration.MoobinConfiguration;

public class MoobinConfigurationImpl implements ConfigurationManager {

	private MoobinConfiguration source;

	public MoobinConfigurationImpl(MoobinConfiguration source) {
		this.source = source;
	}
	
	@Override
	public MoobinConfiguration source() {
		return source;
	}
	
}
