package com.moobin.client;

import com.google.gwt.core.shared.GWT;

public class Moobin {

	private static Cache moobinCache = GWT.create(Cache.class);
	
	public static Cache getCache() {
		return moobinCache;
	}

}
