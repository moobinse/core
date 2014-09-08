package com.moobin.x.sim;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtReference;
import com.moobin.core.data.MetaAction;

@Action({MetaAction.ADD, MetaAction.GET, MetaAction.UPDATE, MetaAction.REMOVE})
public class Account {

	@Id
	public String accountId;
	
	@BtDisplay
	public String name;
	
	@BtReference(Member.class)
	public String organisation;
	
}
