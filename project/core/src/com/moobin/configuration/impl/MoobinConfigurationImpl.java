package com.moobin.configuration.impl;

import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.MoobinConfigurationSource;

public class MoobinConfigurationImpl implements MoobinConfiguration {

	private MoobinConfigurationSource source;

	public MoobinConfigurationImpl(MoobinConfigurationSource source) {
		this.source = source;
	}
	
	@Override
	public MoobinConfigurationSource source() {
		return source;
	}
	
}
