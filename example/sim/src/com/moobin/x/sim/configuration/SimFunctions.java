package com.moobin.x.sim.configuration;

import com.moobin.configuration.impl.GetMethod;
import com.moobin.x.sim.Country;

public class SimFunctions {

	public static GetMethod<Country, String> country_test = 
			new GetMethod<Country, String>((c) -> c.alpha2){};
	
}
