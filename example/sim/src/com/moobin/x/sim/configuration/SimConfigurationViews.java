package com.moobin.x.sim.configuration;

import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.impl.AbstractMoobinConfiguration;
import com.moobin.tools.MoobinConfigurationTool;

public class SimConfigurationViews extends AbstractMoobinConfiguration {

	private SimConfigurationViews() {
		
		inherits(SimConfiguration.class); 
		
	}
	
	public static void main(String[] args) {
		MoobinConfigurationTool.toXml(get(), System.out);
	}

	public static MoobinConfiguration get() {
		return MoobinConfiguration.getConfig(SimConfigurationViews.class);
	}
	
}
