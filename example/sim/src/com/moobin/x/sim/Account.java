package com.moobin.x.sim;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtReference;

public class Account {

	@Id
	public String accountId;
	
	@BtDisplay
	public String name;
	
	@BtReference(Organisation.class)
	public String organisation;
	
}
