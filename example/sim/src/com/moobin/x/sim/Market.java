package com.moobin.x.sim;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.core.data.MetaAction;

@Action({MetaAction.ADD, MetaAction.GET, MetaAction.UPDATE, MetaAction.REMOVE})
public class Market {

	@Id
	public String id;
	
	@Unique
	@BtDisplay
	public String name;
	
}
