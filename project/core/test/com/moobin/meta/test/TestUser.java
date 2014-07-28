package com.moobin.meta.test;

import com.moobin.annotation.BtDisplay;
import com.moobin.annotation.Id;
import com.moobin.annotation.BtReference;
import com.moobin.annotation.Unique;

public class TestUser {

	@Unique
	@Id
	public String userId;
	
	@BtDisplay
	public String name;
	
	@BtReference(TestMember.class)
	public String memberId;

}
