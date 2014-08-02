package com.moobin.meta.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtReference;

public class TestUser {

	@Unique
	@Id
	public String userId;
	
	@BtDisplay
	public String name;
	
	@BtReference(TestMember.class)
	public String memberId;

}
