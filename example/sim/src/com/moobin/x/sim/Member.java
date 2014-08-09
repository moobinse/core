package com.moobin.x.sim;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtText;

public class Member {

	@Id
	public String id;
	
	@BtDisplay
	@Unique
	@BtText(maxLength=20)
	public String name;

}
