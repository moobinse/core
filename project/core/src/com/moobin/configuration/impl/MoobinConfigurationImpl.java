package com.moobin.configuration.impl;

import com.moobin.configuration.ConfigurationManager;
import com.moobin.configuration.MoobinConfiguration;

public class MoobinConfigurationImpl implements ConfigurationManager {

	private MoobinConfiguration source;
	
	private String name;

	public MoobinConfigurationImpl(MoobinConfiguration source) {
		this.source = source;
		name = source.getName();
	}
	
	@Override
	public MoobinConfiguration source() {
		return source;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}
