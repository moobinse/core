package com.moobin.x.sim;

import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtReference;

public class User {

	public String id;
	
	@BtDisplay
	public String name;
	
	@BtReference(Organisation.class)
	public String organisation;
	
}
