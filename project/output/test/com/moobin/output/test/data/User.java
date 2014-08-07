package com.moobin.output.test.data;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtReference;

public class User {

	@Id
	public String userId;
	
	@BtReference(Member.class)
	public String member;
	
	public String name;
	
}
