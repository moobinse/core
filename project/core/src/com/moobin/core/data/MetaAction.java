package com.moobin.core.data;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtReference;
import com.moobin.core.Core;

@Action(MetaAction.GET)
public class MetaAction {

	public static final String GET = "get";
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String REMOVE = "remove";
	
	@Id
	public String key;

	public String action;

	@BtReference(MetaObject.class)
	public String resource;
	
	public MetaAction() {
		
	}
	public MetaAction(String resource, String action) {
		this.action = action;
		this.resource = resource;
		key = action + "/" + resource;
		Core.get().getCacheManager().add(this);
	}
	
}
