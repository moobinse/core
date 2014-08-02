package com.moobin.meta.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;

public class TestMember {

	@Id
	public String memberId;
	
	@BtDisplay
	public String memberName;

}
