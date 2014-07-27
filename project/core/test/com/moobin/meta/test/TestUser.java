package com.moobin.meta.test;

import com.moobin.annotation.Display;
import com.moobin.annotation.Id;
import com.moobin.annotation.Unique;

public class TestUser {

	@Unique
	@Id
	public String userId;
	
	@Display
	public String name;
	
	@Reference(TestMember.class)
	public String memberId;

}
